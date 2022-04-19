package edu.northeastern.cs5500.starterbot.model;

import java.util.Set;
import lombok.Data;
import org.bson.types.ObjectId;

@Data
public class Restaurant implements Model {
    ObjectId id;

    int restaurantId;
    String name;
    String cuisineType;
    Set<Integer> menu;
    boolean isCurrentlyOpen;
    double averageCostPerGuest;
    Set<Integer> ordersPlaced;
    String address;
    int zipcode;
    String imageUrl;
}
