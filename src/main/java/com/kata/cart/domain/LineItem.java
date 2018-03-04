package com.kata.cart.domain;

import io.vavr.control.Option;

import java.util.Objects;
import java.util.UUID;

public class LineItem {
    private final Product product;
    private final int number;

    private LineItem(Product product, int number) {
        this.product = product;
        this.number = number;
    }

    public UUID productId() {
        return product.getId();
    }

    public int numberOfItems() {
        return number;
    }

    public static LineItem of(Product product) {
        return new LineItem(product, 1);
    }

    public double totalPrice() {
        return product.getPrice() * number;
    }

    public boolean isEmpty() {
        return number == 0;
    }

    public LineItem increase() {
        return new LineItem(product, number + 1);
    }

    public Option<LineItem> decrease() {
        if (number == 1) {
            return Option.none();
        }
        return Option.of(new LineItem(product, number - 1));
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        LineItem lineItem = (LineItem) o;
        return number == lineItem.number &&
                Objects.equals(product, lineItem.product);
    }

    @Override
    public int hashCode() {

        return Objects.hash(product, number);
    }
}
