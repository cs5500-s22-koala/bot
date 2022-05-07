package edu.northeastern.cs5500.starterbot.command;

import static com.google.common.truth.Truth.assertThat;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class ClearCartCommandTest {
    ClearCartCommand clearCartCommand;

    @BeforeEach
    void setUp() {
        clearCartCommand = new ClearCartCommand();
    }

    @Test
    void getName() {
        assertEquals(
                clearCartCommand.getCommandData().getDescription(),
                "Tell the bot whether you want to clear shopping cart");
    }

    @Test
    void getCommandData() {
        assertThat(clearCartCommand.getName()).isEqualTo("clear-cart");
    }
}
