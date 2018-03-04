package com.kata.cart;

import com.kata.cart.domain.Cart;
import com.kata.cart.domain.LineItem;
import com.kata.cart.domain.Product;
import com.kata.cart.service.ConsolePrinter;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;

public class CartTest {


    @Test
    public void an_user_can_add_an_article_to_his_cart() {
        Product product = new Product(UUID.randomUUID(), 1.0f);
        Cart cart = Cart.empty().add(product);
        assertThat(cart.items()).contains(LineItem.of(product));
    }

    @Test
    public void an_user_can_remove_an_article_from_his_cart() {
        UUID id = UUID.randomUUID();
        Product product = new Product(id, 1.0f);
        Cart cart = Cart.empty()
                .add(product)
                .remove(id);
        assertThat(cart.items()).isEmpty();
    }

    @Test
    public void an_user_can_see_the_total_price_of_his_cart() {
        Cart cart = Cart.empty()
                .add(new Product(UUID.randomUUID(), 2.0f))
                .add(new Product(UUID.randomUUID(), 8.0f));
        assertThat(cart.totalPrice()).isEqualTo(10);
    }

    @Test
    public void an_user_can_look_at_his_cart() {
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));
        Cart cart = Cart.empty()
                .add(new Product(UUID.fromString("717dcd84-d311-422f-a32b-22ece8b44e31"), 2.0f))
                .add(new Product(UUID.fromString("717dcd84-d311-422f-a32b-22ece8b44e31"), 2.0f))
                .add(new Product(UUID.fromString("b9a3a7d8-9937-4067-9e4d-0fd9c17f1e4d"), 6.0f));
        cart.print(new ConsolePrinter());
        assertThat(outContent.toString()).isEqualTo(
                "1 of b9a3a7d8-9937-4067-9e4d-0fd9c17f1e4d for 6.0$\n" +
                "2 of 717dcd84-d311-422f-a32b-22ece8b44e31 for 4.0$\n"
        );

    }
}
