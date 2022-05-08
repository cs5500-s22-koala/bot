package edu.northeastern.cs5500.starterbot.command;

import static com.google.common.truth.Truth.assertThat;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class AddCustomerCommandTest {
    AddCustomerCommand addCustomerCommand;

    @BeforeEach
    void setUp() {
        addCustomerCommand = new AddCustomerCommand();
    }

    @Test
    void getName() {
        assertThat(addCustomerCommand.getName()).isEqualTo("add-customer");
    }

    @Test
    void getCommandData() {
        assertThat(addCustomerCommand.getCommandData().getDescription())
                .isEqualTo("Tell the bot what customer you want to add");
    }
}
