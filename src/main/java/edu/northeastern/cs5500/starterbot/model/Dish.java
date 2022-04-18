package edu.northeastern.cs5500.starterbot.model;

import lombok.Data;
import org.bson.types.ObjectId;

@Data
public class Dish implements Model {
    ObjectId id;

    int dishId;
    String dishName;
    String dishDescription;
    double price;
    String restaurantName;
}
