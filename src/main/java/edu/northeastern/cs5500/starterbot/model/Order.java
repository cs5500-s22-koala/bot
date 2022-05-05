package edu.northeastern.cs5500.starterbot.model;

import java.time.LocalDateTime;
import java.util.HashMap;
import lombok.Data;
import org.bson.types.ObjectId;

@Data
public class Order implements Model {
    ObjectId id;

    int orderId;
    String customerId; // discordUserId, can be accessed via event.getUser().getId()
    String restaurantName;
    HashMap<String, Integer> itemsOrdered; // {dishname: quantity}
    double totalCharge;
    LocalDateTime orderCreatedTime;
}
