package edu.northeastern.cs5500.starterbot.command;

import static com.google.common.truth.Truth.assertThat;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class GetRestaurantOnZipcodeCommandTest {
    GetRestaurantOnZipcodeCommand getRestaurantOnZipcodeCommand;

    @BeforeEach
    void setUp() {
        getRestaurantOnZipcodeCommand = new GetRestaurantOnZipcodeCommand();
    }

    @Test
    void getName() {
        assertThat(getRestaurantOnZipcodeCommand.getName()).isEqualTo("get-restaurant-zipcode");
    }

    @Test
    void getCommandData() {
        assertThat(getRestaurantOnZipcodeCommand.getCommandData().getDescription())
                .isEqualTo("Tell the bot what zipcode you are looking for");
    }
}
