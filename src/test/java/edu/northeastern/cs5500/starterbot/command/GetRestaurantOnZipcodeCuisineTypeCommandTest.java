package edu.northeastern.cs5500.starterbot.command;

import static com.google.common.truth.Truth.assertThat;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class GetRestaurantOnZipcodeCuisineTypeCommandTest {
    GetRestaurantOnZipcodeCuisineTypeCommand getRestaurantOnZipcodeCuisineTypeCommand;

    @BeforeEach
    void setUp() {
        getRestaurantOnZipcodeCuisineTypeCommand = new GetRestaurantOnZipcodeCuisineTypeCommand();
    }

    @Test
    void getName() {
        assertThat(getRestaurantOnZipcodeCuisineTypeCommand.getName())
                .isEqualTo("get-on-zipcode-cuisintype");
    }

    @Test
    void getCommandData() {
        assertEquals(
                getRestaurantOnZipcodeCuisineTypeCommand.getCommandData().getDescription(),
                "Tell the bot what zipcode and cuisine type you are looking for");
    }
}
