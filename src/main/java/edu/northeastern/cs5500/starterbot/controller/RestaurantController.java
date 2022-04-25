package edu.northeastern.cs5500.starterbot.controller;

import edu.northeastern.cs5500.starterbot.model.Restaurant;
import edu.northeastern.cs5500.starterbot.repository.GenericRepository;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import javax.inject.Inject;

public class RestaurantController {

    GenericRepository<Restaurant> restaurantRepository;

    @Inject
    RestaurantController(GenericRepository<Restaurant> restaurantRepository) {
        this.restaurantRepository = restaurantRepository;
    }

    public void addRestaurant(String name, String cuisineType, int zipcode, String imageUrl) {
        // TODO: better check duplication of restaurant names, only allow unique name
        Restaurant restaurant = new Restaurant();
        restaurant.setCuisineType(cuisineType);
        restaurant.setName(name);
        restaurant.setZipcode(zipcode);
        restaurant.setImageUrl(imageUrl);
        restaurantRepository.add(restaurant);
    }
    // If not found, return null
    public Restaurant getSpecificRestaurantBasedOnName(String restuarantName) {
        Collection<Restaurant> restaurants = restaurantRepository.getAll();
        Restaurant result = null;
        for (Restaurant currentRestaurant : restaurants) {
            if (currentRestaurant.getName().equals(restuarantName)) {
                result = currentRestaurant;
                break;
            }
        }
        return result;
    }
    // If updated return true
    public boolean updateRestaurant(String name, String cuisineType, int zipcode, String imageUrl) {
        boolean foundFlag = false;
        Collection<Restaurant> restaurants = restaurantRepository.getAll();
        for (Restaurant restaurant : restaurants) {
            if (restaurant.getName().equals(name)) {
                restaurant.setCuisineType(cuisineType);
                restaurant.setImageUrl(imageUrl);
                restaurant.setZipcode(zipcode);
                foundFlag = true;
                break;
            }
        }
        return foundFlag;
    }

    // If not found, return empty list
    public List<Restaurant> getRestaurantsBasedOnCuisineType(String cuisineType) {
        Collection<Restaurant> restaurants = restaurantRepository.getAll();
        List<Restaurant> result = new ArrayList<>();
        for (Restaurant currentRestaurant : restaurants) {
            if (currentRestaurant.getCuisineType().equals(cuisineType)) {
                result.add(currentRestaurant);
            }
        }
        return result;
    }

    // If not found, return empty list
    public List<Restaurant> getRestaurantsBasedOnZipcode(int zipcode) {
        Collection<Restaurant> restaurants = restaurantRepository.getAll();
        List<Restaurant> result = new ArrayList<>();
        for (Restaurant currentRestaurant : restaurants) {
            if (currentRestaurant.getZipcode() == zipcode) {
                result.add(currentRestaurant);
            }
        }
        return result;
    }

    // If not found, return empty list
    public List<Restaurant> getRestaurantsBasedOnZipcodeCuisineType(
            int zipcode, String cuisineType) {
        Collection<Restaurant> restaurants = restaurantRepository.getAll();
        List<Restaurant> result = new ArrayList<>();
        for (Restaurant currentRestaurant : restaurants) {
            if (currentRestaurant.getZipcode() == zipcode
                    && currentRestaurant.getCuisineType().equals(cuisineType)) {
                result.add(currentRestaurant);
            }
        }
        return result;
    }
}
