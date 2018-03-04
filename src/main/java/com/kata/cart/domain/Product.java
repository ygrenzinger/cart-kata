package com.kata.cart.domain;

import java.util.Objects;
import java.util.UUID;

public class Product {

    private final UUID id;
    private final float price;

    public Product(UUID id, float price) {
        this.id = id;
        this.price = price;
    }

    public UUID getId() {
        return id;
    }

    public float getPrice() {
        return price;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Product product = (Product) o;
        return Objects.equals(id, product.id) &&
                Objects.equals(price, product.price);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id, price);
    }

    @Override
    public String toString() {
        return "Product{" +
                "id='" + id + '\'' +
                ", price=" + price +
                '}';
    }
}
