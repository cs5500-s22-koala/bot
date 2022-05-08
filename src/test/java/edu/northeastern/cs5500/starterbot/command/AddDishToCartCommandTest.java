package edu.northeastern.cs5500.starterbot.command;

import static com.google.common.truth.Truth.assertThat;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class AddDishToCartCommandTest {
    AddDishToCartCommand addDishToCartCommand;

    @BeforeEach
    void setUp() {
        addDishToCartCommand = new AddDishToCartCommand();
    }

    @Test
    void testGetName() {
        assertThat(addDishToCartCommand.getName()).isEqualTo("add-dish-to-cart");
    }

    @Test
    void testGetCommandData() {
        assertThat(addDishToCartCommand.getCommandData().getDescription())
                .isEqualTo("Tell the bot what dish you want to add to cart");
    }
}
