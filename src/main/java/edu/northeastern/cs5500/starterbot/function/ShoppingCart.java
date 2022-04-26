package edu.northeastern.cs5500.starterbot.function;

import edu.northeastern.cs5500.starterbot.model.Dish;
import java.util.HashMap;

public class ShoppingCart {
    private static ShoppingCart cart_instance = null;

    private HashMap<String, HashMap<Dish, Integer>>
            cart; // each discord user id has his/her own cart
    private HashMap<String, String>
            user_to_restaurant; // track which restaurant the user is currently adding dish from

    private ShoppingCart() {
        cart = new HashMap<>();
        user_to_restaurant = new HashMap<>();
    }

    public static ShoppingCart getInstance() {
        if (cart_instance == null) {
            cart_instance = new ShoppingCart();
        }
        return cart_instance;
    }

    public void addDishToCart(String discordUserId, Dish dish, int quantity) {
        if (!cart.containsKey(discordUserId)) {
            cart.put(discordUserId, new HashMap<Dish, Integer>());
            cart.get(discordUserId).put(dish, quantity);
            user_to_restaurant.put(discordUserId, dish.getRestaurantName());
        } else {
            HashMap<Dish, Integer> user_cart = cart.get(discordUserId);
            if (user_cart.containsKey(dish)) {
                user_cart.put(dish, user_cart.get(dish) + quantity);
            } else {
                user_cart.putIfAbsent(dish, quantity);
            }
        }
    }

    // return cart for a discord user, return value could be null
    public HashMap<Dish, Integer> getCartOfUser(String discordUserId) {
        return this.cart.get(discordUserId);
    }

    // clear a specific user's shopping cart
    public void clearShoppingCartOfUser(String discordUserId) {
        cart.remove(discordUserId);
        user_to_restaurant.remove(discordUserId);
    }

    // return the name of current restaurant a user is shopping at, could be null
    public String getCurrentRestaurantOfUser(String discordUserId) {
        return user_to_restaurant.get(discordUserId);
    }

    public String displayCartInfoOfUser(String discordUserId) {
        StringBuilder sb = new StringBuilder();
        HashMap<Dish, Integer> user_cart = cart.get(discordUserId);
        sb.append("Your shopping cart info (item: amount):\n");
        for (Dish dish : user_cart.keySet()) {
            sb.append(dish.getDishName() + ": " + user_cart.get(dish) + "\n");
        }
        sb.append("Total price: " + getPriceOfCartForUser(discordUserId) + "\n");
        return sb.toString();
    }

    public Double getPriceOfCartForUser(String discordUserId) {
        Double cost = 0.0;
        HashMap<Dish, Integer> user_cart = cart.get(discordUserId);
        for (Dish dish : user_cart.keySet()) {
            cost += dish.getPrice() * user_cart.get(dish);
        }
        return cost;
    }
}
