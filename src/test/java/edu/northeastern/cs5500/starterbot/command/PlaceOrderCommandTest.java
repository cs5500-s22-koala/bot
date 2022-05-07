package edu.northeastern.cs5500.starterbot.command;

import static com.google.common.truth.Truth.assertThat;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class PlaceOrderCommandTest {
    PlaceOrderCommand placeOrderCommand;

    @BeforeEach
    void setUp() {
        placeOrderCommand = new PlaceOrderCommand();
    }

    @Test
    void getName() {
        assertEquals(
                placeOrderCommand.getCommandData().getDescription(),
                "Tell the bot whether you want to place order");
    }

    @Test
    void getCommandData() {
        assertThat(placeOrderCommand.getName()).isEqualTo("place-order");
    }
}
