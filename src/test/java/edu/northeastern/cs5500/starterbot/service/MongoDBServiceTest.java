package edu.northeastern.cs5500.starterbot.service;

import static com.google.common.truth.Truth.assertThat;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class MongoDBServiceTest {

    @Test
    void getDatabaseURI() {
        String URL = MongoDBService.getDatabaseURI();
        assertThat(URL).isNotNull();
    }
}
