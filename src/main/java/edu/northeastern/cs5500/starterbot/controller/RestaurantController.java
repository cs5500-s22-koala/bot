package edu.northeastern.cs5500.starterbot.controller;

import edu.northeastern.cs5500.starterbot.model.Restaurant;
import edu.northeastern.cs5500.starterbot.repository.GenericRepository;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import javax.annotation.Nonnull;
import javax.inject.Inject;

public class RestaurantController {

    GenericRepository<Restaurant> restaurantRepository;

    @Inject
    RestaurantController(GenericRepository<Restaurant> restaurantRepository) {
        this.restaurantRepository = restaurantRepository;

        // if (restaurantRepository.count() == 0) {
        //     Restaurant restaurant = new Restaurant();
        //     restaurant.setName("Koala Restaurant");
        //     restaurant.setCuisineType("Chinese");
        //     restaurantRepository.add(restaurant);
        // }
    }

    public void addRestaurant(String name, String cuisineType, long zipcode, String imageUrl) {
        // TODO: better check duplication of restaurant names, only allow unique name
        Restaurant restaurant = new Restaurant();
        restaurant.setCuisineType(cuisineType);
        restaurant.setName(name);
        restaurant.setZipcode(zipcode);
        restaurant.setImageUrl(imageUrl);
        restaurantRepository.add(restaurant);
    }

    //    @Nonnull  // it could be Null, right?
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

    @Nonnull
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

    @Nonnull
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
}
