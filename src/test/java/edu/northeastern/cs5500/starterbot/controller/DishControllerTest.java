package edu.northeastern.cs5500.starterbot.controller;

import static com.google.common.truth.Truth.assertThat;

import edu.northeastern.cs5500.starterbot.repository.InMemoryRepository;
import org.junit.jupiter.api.Test;

public class DishControllerTest {

    static final int dishId1 = 1;
    static final String dishName1 = "dish1";
    static final double price1 = 1.23;
    static final String restaurantName1 = "res1";
    static final double price2 = 4.56;
    static final String restaurantName2 = "res2";

    private DishController getDishController() {
        DishController dishController = new DishController(new InMemoryRepository<>());
        return dishController;
    }

    @Test
    public void testFindANullDish() {
        DishController dishController = getDishController();
        dishController.addDish(dishId1, dishName1, price1, restaurantName1);
        assertThat(dishController.findADish(dishName1, restaurantName2)).isEqualTo(null);
    }

    @Test
    public void testFindANonNullDish() {
        DishController dishController = getDishController();
        dishController.addDish(dishId1, dishName1, price1, restaurantName1);
        assertThat(dishController.findADish(dishName1, restaurantName1).getDishName())
                .isEqualTo(dishName1);
    }

    @Test
    public void testUpdatePrice() {
        // before price update
        DishController dishController = getDishController();
        dishController.addDish(dishId1, dishName1, price1, restaurantName1);
        assertThat(dishController.findADish(dishName1, restaurantName1).getPrice())
                .isEqualTo(price1);

        // after price update
        dishController.updateDishPrice(dishId1, price2);
        assertThat(dishController.findADish(dishName1, restaurantName1).getPrice())
                .isEqualTo(price2);
    }

    @Test
    public void testGetMenuOfARestaurant() {
        // return an empty list as menu
        DishController dishController = getDishController();
        dishController.addDish(dishId1, dishName1, price1, restaurantName1);
        assertThat(dishController.getMenuOfARestaurant(restaurantName2).size()).isEqualTo(0);

        // return a non-empty list of menu
        dishController.addDish(dishId1, dishName1, price1, restaurantName2);
        assertThat(dishController.getMenuOfARestaurant(restaurantName2).size()).isEqualTo(1);
    }
}
