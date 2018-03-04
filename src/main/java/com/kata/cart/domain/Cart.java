package com.kata.cart.domain;

import io.vavr.collection.HashMap;
import io.vavr.collection.Map;
import io.vavr.collection.Seq;

import java.util.UUID;

public class Cart {

    private final Map<UUID, LineItem> items;

    private Cart(Map<UUID, LineItem> items) {
        this.items = items;
    }

    public static Cart empty() {
        return new Cart(HashMap.empty());
    }

    public Seq<LineItem> items() {
        return items.values();
    }

    public Cart add(Product product) {
        return new Cart(items.put(product.getId(), LineItem.of(product), (lineItem, lineItem2) -> lineItem.increase()));
    }

    public Cart remove(UUID id) {
        return items.get(id)
                .flatMap(LineItem::decrease)
                .map(l -> new Cart(items.put(id, l)))
                .getOrElse(new Cart(items.remove(id)));
    }

    public double totalPrice() {
        return items.values().toJavaStream().mapToDouble(LineItem::totalPrice).sum();
    }

    public void print(Printer printer) {
        items.values().forEach(printer::print);
    }
}
