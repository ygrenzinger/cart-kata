package com.kata.cart.service;

import com.kata.cart.domain.LineItem;
import com.kata.cart.domain.Product;
import com.kata.cart.domain.Printer;

public class ConsolePrinter implements Printer {
    @Override
    public void print(LineItem lineItem) {
        System.out.println(lineItem.numberOfItems() + " of " + lineItem.productId() + " for " + lineItem.totalPrice() + "$");
    }
}
