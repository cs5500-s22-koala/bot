package edu.northeastern.cs5500.starterbot.command;

import static com.google.common.truth.Truth.assertThat;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class CheckOrderStatusCommandTest {
    CheckOrderStatusCommand checkOrderStatusCommand;

    @BeforeEach
    void setUp() {
        checkOrderStatusCommand = new CheckOrderStatusCommand();
    }

    @Test
    void getName() {
        assertThat(checkOrderStatusCommand.getName()).isEqualTo("check-order-status");
    }

    @Test
    void getCommandData() {
        assertEquals(
                checkOrderStatusCommand.getCommandData().getDescription(),
                "Tell the bot if you want to check order status");
    }
}
