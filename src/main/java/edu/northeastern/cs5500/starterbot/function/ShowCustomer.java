package edu.northeastern.cs5500.starterbot.function;

import com.mongodb.Block;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;
import edu.northeastern.cs5500.starterbot.model.Customer;
import java.util.ArrayList;
import java.util.List;
import org.bson.Document;

/** This class helps to show a user's watched list. */
public class ShowCustomer {
    private MongoClient mongoClient;

    /** Constructor of showWatched. */
    public ShowCustomer() {}

    public List<Customer> showCustomerList(String customerName) {
        mongoClient =
                MongoClients.create(
                        "mongodb+srv://koala:koala@charbotdb.5epta.mongodb.net/myFirstDatabase?retryWrites=true&w=majority");
        MongoDatabase database = mongoClient.getDatabase("CharBotDB");
        MongoCollection<Document> collection = database.getCollection("Customer");

        List<Customer> list = new ArrayList<>();
        Block<Document> printBlock =
                new Block<Document>() {
                    @Override
                    public void apply(final Document doc) {
                        Customer customer = new Customer();
                        customer.setCustomerName(doc.getString("customerId"));
                        customer.setCustomerName(doc.getString("customerName"));
                        // customer.setCustomerName(doc.getString("phone"));
                        // customer.setCustomerName(doc.getString("address"));
                        list.add(customer);
                    }
                };

        collection.find(Filters.eq("customerName", customerName)).forEach(printBlock);
        return list;
    }
}
