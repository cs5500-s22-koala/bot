package edu.northeastern.cs5500.starterbot.controller;

import static com.google.common.truth.Truth.assertThat;
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
        assertThat(restaurant.getName()).isEqualTo(name);
        assertThat(restaurant.getAddress()).isEqualTo(address);
        assertThat(restaurant.getCuisineType()).isEqualTo(cuisineType);
        assertThat(restaurant.getZipcode()).isEqualTo(zipcode);
        assertThat(restaurant.getImageUrl()).isEqualTo(imageUrl);
        assertThat(restaurant.getIntroduction()).isEqualTo(introduction);
        assertThat(restaurant.getOperatingHours()).isEqualTo(operatingHours);
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
        assertThat(restaurant.getName()).isEqualTo(name);
        assertThat(restaurant.getAddress()).isEqualTo(address);
        assertThat(restaurant.getCuisineType()).isEqualTo(cuisineType);
        assertThat(restaurant.getZipcode()).isEqualTo(zipcode);
        assertThat(restaurant.getImageUrl()).isEqualTo(imageUrl);
        assertThat(restaurant.getIntroduction()).isEqualTo(introduction);
        assertThat(restaurant.getOperatingHours()).isEqualTo(operatingHours);

        // Search name that does not exit in database
        Restaurant restaurant2 = restaurantController.getSpecificRestaurantBasedOnName(name2);
        assertThat(restaurant2).isNull();
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
        assertThat(restaurantController.getSpecificRestaurantBasedOnName(name).getCuisineType())
                .isEqualTo(cuisineTypeUpdated);
    }

    @Test
    void testGetRestaurantsBasedOnZipcode() {
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

        assertThat(restaurantController.getRestaurantsBasedOnZipcode(zipcode).size() == 1);

        assertThat(restaurantController.getRestaurantsBasedOnZipcode(12053).size() == 0);
    }

    @Test
    void testGetRestaurantsBasedOnZipcodeCuisineType() {
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

        assertThat(
                restaurantController
                                .getRestaurantsBasedOnZipcodeCuisineType(zipcode, cuisineType)
                                .size()
                        == 1);

        assertThat(
                restaurantController
                                .getRestaurantsBasedOnZipcodeCuisineType(12053, cuisineType)
                                .size()
                        == 0);

        assertThat(
                restaurantController.getRestaurantsBasedOnZipcodeCuisineType(zipcode, "Moon").size()
                        == 0);
    }
}
