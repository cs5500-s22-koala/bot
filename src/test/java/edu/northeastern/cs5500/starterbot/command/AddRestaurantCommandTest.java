package edu.northeastern.cs5500.starterbot.command;

import static com.google.common.truth.Truth.assertThat;
import static org.junit.jupiter.api.Assertions.*;

import net.dv8tion.jda.api.interactions.commands.build.CommandData;
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
        CommandData commandData = addRestaurantCommand.getCommandData();
        System.out.println(commandData);
    }

    @Test
    void onEvent() {}
}
