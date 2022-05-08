package edu.northeastern.cs5500.starterbot.command;

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
        assertEquals(welcomeCommand.getName(), "welcome");
    }

    @Test
    void getCommandData() {
        assertEquals(welcomeCommand.getCommandData().getDescription(), "Welcome");
    }
}
