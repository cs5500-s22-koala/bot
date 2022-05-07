package edu.northeastern.cs5500.starterbot.command;

import static com.google.common.truth.Truth.assertThat;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class GetRestaurantCommandTest {
    GetRestaurantCommand getRestaurantCommand;

    @BeforeEach
    void setUp() {
        getRestaurantCommand = new GetRestaurantCommand();
    }

    @Test
    void getName() {
        assertThat(getRestaurantCommand.getName()).isEqualTo("get-restaurant");
    }

    @Test
    void getCommandData() {
        assertEquals(
                getRestaurantCommand.getCommandData().getDescription(),
                "Tell the bot what restaurant name is");
    }
}
