package edu.northeastern.cs5500.starterbot.command;

import static com.google.common.truth.Truth.assertThat;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class WelcomeCommandTest {
    private WelcomeCommand welcomeCommand;

    @BeforeEach
    void setUp() {
        welcomeCommand = new WelcomeCommand();
    }

    @Test
    void getName() {
        assertThat(welcomeCommand.getName()).isEqualTo("welcome");
    }

    @Test
    void getCommandData() {
        assertThat(welcomeCommand.getCommandData().getDescription()).isEqualTo("Welcome");
    }
}
