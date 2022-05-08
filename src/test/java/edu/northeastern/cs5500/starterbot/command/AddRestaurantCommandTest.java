package edu.northeastern.cs5500.starterbot.command;

import static com.google.common.truth.Truth.assertThat;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class AddRestaurantCommandTest {
    AddRestaurantCommand addRestaurantCommand;

    @BeforeEach
    void setUp() {
        addRestaurantCommand = new AddRestaurantCommand();
    }

    @Test
    void getName() {
        assertThat(addRestaurantCommand.getName()).isEqualTo("add-restaurant");
    }

    @Test
    void getCommandData() {
        assertThat(addRestaurantCommand.getCommandData().getDescription())
                .isEqualTo("Tell the bot what restaurant you want to add");
    }
}
