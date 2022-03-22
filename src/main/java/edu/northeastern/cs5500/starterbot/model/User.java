package edu.northeastern.cs5500.starterbot.model;

import lombok.Data;
import org.bson.types.*;

@Data
public class User implements Model {
    ObjectId id;
    String username;
}
