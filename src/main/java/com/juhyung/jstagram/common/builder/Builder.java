package com.juhyung.jstagram.common.builder;

import org.assertj.core.util.Lists;

import java.util.List;
import java.util.function.BiConsumer;
import java.util.function.Consumer;
import java.util.function.Supplier;
/*
* Java 8의 consumer, supplier를 활용한 Generic Builder 패턴
* */
public class Builder<T> {
    private final Supplier<T> anything;
    private List<Consumer<T>> any = Lists.newArrayList();

    private Builder(Supplier<T> anything) {
        this.anything = anything;
    }

    public static <T> Builder<T> of(Supplier<T> anything) {
        return new Builder(anything);
    }

    public <U> Builder<T> with(BiConsumer<T, U> consumer, U value) {
        any.add(instance -> consumer.accept(instance, value));
        return this;
    }

    public T build() {
      T value = anything.get();
      any.forEach(consumer -> consumer.accept(value));
      return value;
    }
}
