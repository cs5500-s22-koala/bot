package edu.northeastern.cs5500.starterbot.controller;

import edu.northeastern.cs5500.starterbot.model.Restaurant;

public class RestaurantInsert {
    public static void saveRestaurant(Restaurant restaurant) {
        // MongoDB.getInstance().getCollection("Restaurant").insertOne(restaurant.toDocument());
    }
}
