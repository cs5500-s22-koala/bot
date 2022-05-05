package edu.northeastern.cs5500.starterbot.model;

import lombok.Data;
import org.bson.types.ObjectId;

@Data
public class Restaurant implements Model {
    ObjectId id;
    String name;
    String cuisineType;
    double averageCostPerGuest;
    String address;
    int zipcode;
    String imageUrl;
    int phone;
    String operatingHours;
    String introduction;
}
