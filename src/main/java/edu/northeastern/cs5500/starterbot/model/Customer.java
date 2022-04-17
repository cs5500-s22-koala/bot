package edu.northeastern.cs5500.starterbot.model;

import lombok.Data;
import org.bson.types.ObjectId;

@Data
public class Customer implements Model {
    ObjectId id;
    int customerId;
    String customerName;
    int phone;
    String address;
    String bankAccount;
}
