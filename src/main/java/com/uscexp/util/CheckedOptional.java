package com.uscexp.util;

import com.uscexp.util.function.CheckedConsumer;
import com.uscexp.util.function.CheckedFunction;
import com.uscexp.util.function.CheckedPredicate;

import java.util.NoSuchElementException;
import java.util.Objects;
import java.util.concurrent.Callable;
import java.util.function.Supplier;

public class CheckedOptional<T> {
    private static final CheckedOptional<?> EMPTY = new CheckedOptional();
    private final T value;

    private CheckedOptional() {
        this.value = null;
    }

    public static <T> CheckedOptional<T> empty() {
        CheckedOptional var0 = EMPTY;
        return var0;
    }

    private CheckedOptional(T var1) {
        this.value = Objects.requireNonNull(var1);
    }

    public static <T> CheckedOptional<T> of(T var0) {
        return new CheckedOptional(var0);
    }

    public static <T> CheckedOptional<T> ofNullable(T var0) {
        return var0 == null ? empty() : of(var0);
    }

    public T get() {
        if (this.value == null) {
            throw new NoSuchElementException("No value present");
        } else {
            return this.value;
        }
    }

    public boolean isPresent() {
        return this.value != null;
    }

    public void ifPresent(CheckedConsumer<? super T> var1) throws Exception {
        if (this.value != null) {
            var1.accept(this.value);
        }

    }

    public CheckedOptional<T> filter(CheckedPredicate<? super T> var1) throws Exception {
        Objects.requireNonNull(var1);
        if (!this.isPresent()) {
            return this;
        } else {
            return var1.test(this.value) ? this : empty();
        }
    }

    public <U> CheckedOptional<U> map(CheckedFunction<? super T, ? extends U> var1) throws Exception {
        Objects.requireNonNull(var1);
        return !this.isPresent() ? empty() : ofNullable(var1.apply(this.value));
    }

    public <U> CheckedOptional<U> flatMap(CheckedFunction<? super T, CheckedOptional<U>> var1) throws Exception {
        Objects.requireNonNull(var1);
        return !this.isPresent() ? empty() : (CheckedOptional)Objects.requireNonNull(var1.apply(this.value));
    }

    public T orElse(T var1) {
        return this.value != null ? this.value : var1;
    }

    public T orElseGet(Supplier<? extends T> var1) {
        return this.value != null ? this.value : var1.get();
    }

    public <X extends Throwable> T orElseThrow(Callable<? extends X> var1) throws X, Exception {
        if (this.value != null) {
            return this.value;
        } else {
            throw (X)var1.call();
        }
    }

    public boolean equals(Object var1) {
        if (this == var1) {
            return true;
        } else if (!(var1 instanceof CheckedOptional)) {
            return false;
        } else {
            CheckedOptional var2 = (CheckedOptional)var1;
            return Objects.equals(this.value, var2.value);
        }
    }

    public int hashCode() {
        return Objects.hashCode(this.value);
    }

    public String toString() {
        return this.value != null ? String.format("CheckedOptional[%s]", this.value) : "CheckedOptional.empty";
    }
}
