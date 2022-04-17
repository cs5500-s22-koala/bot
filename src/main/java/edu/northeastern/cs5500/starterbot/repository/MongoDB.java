package edu.northeastern.cs5500.starterbot.repository;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;

public class MongoDB {
    private static MongoDB instance;
    private MongoClient mongoClient;
    ProcessBuilder processBuilder = new ProcessBuilder();
    final String databaseURI = processBuilder.environment().get("MONGODB_URI");

    private MongoDB() {
        mongoClient = MongoClients.create(databaseURI);
    }

    public static MongoDB getInstance() {
        if (instance == null) instance = new MongoDB();
        return instance;
    }

    public MongoCollection<Document> getCollection(String collection) {

        MongoDatabase database = mongoClient.getDatabase("CharBotDB");
        if (database.getCollection(collection) == null) {
            database.createCollection(collection);
        }
        return database.getCollection(collection);
    }
}
