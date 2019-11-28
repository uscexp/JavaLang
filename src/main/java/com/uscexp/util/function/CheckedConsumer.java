package com.uscexp.util.function;

import java.util.Objects;

@FunctionalInterface
public interface CheckedConsumer<T> {
    void accept(T var1) throws Exception;

    default CheckedConsumer<T> andThen(CheckedConsumer<? super T> var1) {
        Objects.requireNonNull(var1);
        return (var2) -> {
            this.accept(var2);
            var1.accept(var2);
        };
    }
}
