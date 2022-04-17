package edu.northeastern.cs5500.starterbot.controller;

import com.mongodb.Block;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.model.Filters;
import com.mongodb.client.model.Sorts;
import edu.northeastern.cs5500.starterbot.model.Restaurant;
import edu.northeastern.cs5500.starterbot.repository.MongoDB;
import java.util.ArrayList;
import java.util.List;
import org.bson.Document;
import org.bson.conversions.Bson;

public class RestaurantLoad {
    @SuppressWarnings("deprecation")
    public static List<Restaurant> loadRestaurant(String restaurantName) {
        MongoCollection<Document> collection = MongoDB.getInstance().getCollection("Restaurant");

        List<Restaurant> list = new ArrayList<>();
        Block<Document> printBlock =
                new Block<Document>() {
                    @Override
                    public void apply(final Document doc) {
                        Restaurant restaurant = new Restaurant();
                        restaurant.setName(doc.getString("name"));
                        restaurant.setCuisineType(doc.getString("cuisineType"));
                        restaurant.setZipcode(doc.getInteger("zipcode"));
                        list.add(restaurant);
                    }
                };
        Bson filters = Filters.eq("name", restaurantName);
        collection.find(filters).sort(Sorts.ascending("name")).forEach(printBlock);
        return list;
    }
}
