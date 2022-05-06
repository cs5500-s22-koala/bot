package edu.northeastern.cs5500.starterbot.command;

import static com.google.common.truth.Truth.assertThat;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class GetCustomerOnNameCommandTest {
    GetCustomerOnNameCommand getCustomerOnNameCommand;

    @BeforeEach
    void setUp() {
        getCustomerOnNameCommand = new GetCustomerOnNameCommand();
    }

    @Test
    void getName() {
        assertThat(getCustomerOnNameCommand.getName()).isEqualTo("get-customer-name");
    }

    @Test
    void getCommandData() {
        assertEquals(
                getCustomerOnNameCommand.getCommandData().getDescription(),
                "Tell the bot what customer you are looking for");
    }
}
