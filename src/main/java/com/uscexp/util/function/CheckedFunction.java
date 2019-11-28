package com.uscexp.util.function;

import java.util.Objects;

@FunctionalInterface
public interface CheckedFunction<T, R> {
    R apply(T var1) throws Exception;

    default <V> CheckedFunction<V, R> compose(CheckedFunction<? super V, ? extends T> var1) {
        Objects.requireNonNull(var1);
        return (var2) -> {
            return this.apply(var1.apply(var2));
        };
    }

    default <V> CheckedFunction<T, V> andThen(CheckedFunction<? super R, ? extends V> var1) {
        Objects.requireNonNull(var1);
        return (var2) -> {
            return var1.apply(this.apply(var2));
        };
    }

    static <T> CheckedFunction<T, T> identity() {
        return (var0) -> {
            return var0;
        };
    }
}
