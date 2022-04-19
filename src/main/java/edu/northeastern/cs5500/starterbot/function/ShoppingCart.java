package edu.northeastern.cs5500.starterbot.function;

import edu.northeastern.cs5500.starterbot.model.Dish;
import java.util.HashMap;

public class ShoppingCart {
    private static ShoppingCart cart_instance = null;

    private HashMap<Dish, Integer> cart;

    private ShoppingCart() {
        cart = new HashMap<>();
    }

    public static ShoppingCart getInstance() {
        if (cart_instance == null) {
            cart_instance = new ShoppingCart();
        }
        return cart_instance;
    }

    public void addDishToCart(Dish dish, int quantity) {
        if (cart.containsKey(dish)) {
            cart.put(dish, cart.get(dish) + quantity);
        } else {
            cart.putIfAbsent(dish, quantity);
        }
    }

    public HashMap<Dish, Integer> getCart() {
        return this.cart;
    }

    public void clearShoppingCart() {
        cart.clear();
    }
}
