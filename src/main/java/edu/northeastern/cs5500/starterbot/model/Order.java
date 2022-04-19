package edu.northeastern.cs5500.starterbot.model;

import java.util.HashMap;
import lombok.Data;
import org.bson.types.ObjectId;

@Data
public class Order implements Model {
    ObjectId id;

    int orderId;
    String customerId; // discordUserId, can be accessed via event.getUser().getId()
    String restaurantName;
    HashMap<Dish, Integer> itemsOrdered;
    double totalCharge;
    OrderStatus orderStatus;

    // TODO: add attributes of createdTime and estimatedTime

}
