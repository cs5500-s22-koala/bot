package edu.northeastern.cs5500.starterbot.command;

import static com.google.common.truth.Truth.assertThat;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class AddDishCommandTest {
    AddDishCommand addDishCommand;

    @BeforeEach
    void setUp() {
        addDishCommand = new AddDishCommand();
    }

    @Test
    void getName() {
        assertThat(addDishCommand.getName()).isEqualTo("add-dish");
    }

    @Test
    void getCommandData() {
        assertThat(addDishCommand.getCommandData().getDescription())
                .isEqualTo("Tell the bot what dish you want to add");
    }
}
