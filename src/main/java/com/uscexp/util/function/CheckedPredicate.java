package com.uscexp.util.function;

import java.util.Objects;

@FunctionalInterface
public interface CheckedPredicate<T> {
    boolean test(T var1) throws Exception;

    default CheckedPredicate<T> and(CheckedPredicate<? super T> var1) {
        Objects.requireNonNull(var1);
        return (var2) -> {
            return this.test(var2) && var1.test(var2);
        };
    }

    default CheckedPredicate<T> negate() {
        return (var1) -> {
            return !this.test(var1);
        };
    }

    default CheckedPredicate<T> or(CheckedPredicate<? super T> var1) {
        Objects.requireNonNull(var1);
        return (var2) -> {
            return this.test(var2) || var1.test(var2);
        };
    }

    static <T> CheckedPredicate<T> isEqual(Object var0) {
        return null == var0 ? Objects::isNull : (var1) -> {
            return var0.equals(var1);
        };
    }
}
