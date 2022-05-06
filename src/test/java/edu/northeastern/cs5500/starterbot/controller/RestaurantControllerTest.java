package edu.northeastern.cs5500.starterbot.controller;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.*;

import edu.northeastern.cs5500.starterbot.model.Restaurant;
import edu.northeastern.cs5500.starterbot.repository.InMemoryRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class RestaurantControllerTest {

    static final String name = "Test";
    static final String name2 = "Test2";
    static final String cuisineType = "TestType";
    static final String cuisineTypeUpdated = "TestTypeUpdated";
    static final double averageCostPerGuest = 10;
    static final String address = "Test address";
    static final int zipcode = 100058;
    static final String imageUrl = " TestURL";
    static final int phone = 123456789;
    static final String operatingHours = "8AM-8PM";
    static final String introduction = "Test intro";
    RestaurantController restaurantController;

    @BeforeEach
    void setUp() {
        restaurantController = new RestaurantController(new InMemoryRepository<>());
    }

    @Test
    void testAddRestaurant() {
        restaurantController.addRestaurant(
                name,
                cuisineType,
                averageCostPerGuest,
                address,
                zipcode,
                imageUrl,
                phone,
                operatingHours,
                introduction);
        Restaurant restaurant = restaurantController.getSpecificRestaurantBasedOnName(name);
        assertEquals(restaurant.getName(), name);
        assertEquals(restaurant.getAddress(), address);
        assertEquals(restaurant.getCuisineType(), cuisineType);
        assertTrue(restaurant.getZipcode() == zipcode);
        assertEquals(restaurant.getImageUrl(), imageUrl);
        assertEquals(restaurant.getIntroduction(), introduction);
        assertEquals(restaurant.getOperatingHours(), operatingHours);
    }

    @Test
    void testGetSpecificRestaurantBasedOnName() {
        restaurantController.addRestaurant(
                name,
                cuisineType,
                averageCostPerGuest,
                address,
                zipcode,
                imageUrl,
                phone,
                operatingHours,
                introduction);
        Restaurant restaurant = restaurantController.getSpecificRestaurantBasedOnName(name);
        assertEquals(restaurant.getName(), name);
        assertEquals(restaurant.getAddress(), address);
        assertEquals(restaurant.getCuisineType(), cuisineType);
        assertTrue(restaurant.getZipcode() == zipcode);
        assertEquals(restaurant.getImageUrl(), imageUrl);
        assertEquals(restaurant.getIntroduction(), introduction);
        assertEquals(restaurant.getOperatingHours(), operatingHours);

        // Search name that does not exit in database
        Restaurant restaurant2 = restaurantController.getSpecificRestaurantBasedOnName(name2);
        assertTrue(restaurant2 == null);
    }

    @Test
    void testUpdateRestaurant() {
        restaurantController.addRestaurant(
                name,
                cuisineType,
                averageCostPerGuest,
                address,
                zipcode,
                imageUrl,
                phone,
                operatingHours,
                introduction);
        restaurantController.updateRestaurant(
                name,
                cuisineTypeUpdated,
                averageCostPerGuest,
                address,
                zipcode,
                imageUrl,
                phone,
                operatingHours,
                introduction);
        assertEquals(
                restaurantController.getSpecificRestaurantBasedOnName(name).getCuisineType(),
                cuisineTypeUpdated);
    }
}
