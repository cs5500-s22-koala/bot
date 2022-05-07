package edu.northeastern.cs5500.starterbot.function;

import static org.junit.Assert.*;

import edu.northeastern.cs5500.starterbot.controller.DishController;
import edu.northeastern.cs5500.starterbot.model.Dish;
import edu.northeastern.cs5500.starterbot.repository.InMemoryRepository;
import org.junit.Before;
import org.junit.Test;

public class ShoppingCartTest {
    ShoppingCart shoppingCart;
    DishController dishController;
    static final String userId1 = "1";
    static final String dishname1 = "dish1";
    static final String restaurantname1 = "restaurant1";
    static final int dishId1 = 1;
    static final Double price1 = 1.23;
    static final int quantity1 = 1;
    static final String userId2 = "2";

    private DishController getDishController() {
        DishController dishController = new DishController(new InMemoryRepository<>());
        return dishController;
    }

    @Before
    public void setUp() throws Exception {
        shoppingCart = ShoppingCart.getInstance();
        dishController = getDishController();
    }

    @Test
    public void testAddDishToCart() {
        // before add any dish to cart of user
        assertNull(shoppingCart.getCartOfUser(userId1));

        // after add one dish to cart of user
        dishController.addDish(dishId1, dishname1, price1, restaurantname1);
        Dish dishToAdd = dishController.findADish(dishname1, restaurantname1);
        shoppingCart.addDishToCart(userId1, dishToAdd, quantity1);
        assertEquals(shoppingCart.getCartOfUser(userId1).size(), 1);
    }

    @Test
    public void testClearShoppingCartOfUser() {
        shoppingCart.clearShoppingCartOfUser(userId1);
        assertEquals(shoppingCart.getCart().size(), 0);
        assertEquals(shoppingCart.getUser_to_restaurant().size(), 0);
    }

    @Test
    public void testGetCurrentRestaurantOfUser() {
        dishController.addDish(dishId1, dishname1, price1, restaurantname1);
        Dish dishToAdd = dishController.findADish(dishname1, restaurantname1);
        shoppingCart.addDishToCart(userId1, dishToAdd, quantity1);
        assertEquals(shoppingCart.getCurrentRestaurantOfUser(userId1), restaurantname1);
    }

    @Test
    public void testDisplayCartInfoOfUser() {
        String expectedMsg = shoppingCart.displayCartInfoOfUser(userId1);
        StringBuilder actualMsg = new StringBuilder();
        actualMsg
                .append(dishname1)
                .append(": ")
                .append(quantity1)
                .append("\n")
                .append("Total price: $")
                .append(price1)
                .append("\n");
        assertEquals(expectedMsg, actualMsg.toString());
    }

    @Test
    public void testGetPriceOfCartForUser() {
        assertEquals(shoppingCart.getPriceOfCartForUser(userId1), price1);
        assertEquals(shoppingCart.getPriceOfCartForUser(userId2), (Double) 0.0);
    }

}
