package edu.northeastern.cs5500.starterbot.service;

import static com.google.common.truth.Truth.assertThat;
import static org.junit.jupiter.api.Assertions.*;

import edu.northeastern.cs5500.starterbot.model.Restaurant;
import edu.northeastern.cs5500.starterbot.repository.GenericRepository;
import edu.northeastern.cs5500.starterbot.repository.MongoDBRepository;
import org.junit.jupiter.api.Test;

class MongoDBServiceTest {

    @Test
    void getDatabaseURI() {
        String URL = MongoDBService.getDatabaseURI();
        assertThat(URL).isNotNull();
    }

}
