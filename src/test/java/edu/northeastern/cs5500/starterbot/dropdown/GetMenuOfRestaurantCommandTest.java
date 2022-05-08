package edu.northeastern.cs5500.starterbot.dropdown;

import static com.google.common.truth.Truth.assertThat;

import edu.northeastern.cs5500.starterbot.controller.DishController;
import edu.northeastern.cs5500.starterbot.controller.OrderController;
import edu.northeastern.cs5500.starterbot.function.ShoppingCart;
import edu.northeastern.cs5500.starterbot.repository.InMemoryRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class GetMenuOfRestaurantCommandTest {
    GetMenuOfRestaurantCommand getMenuOfRestaurantCommand;
    ShoppingCart shoppingCart;
    DishController dishController;
    OrderController orderController;

    private OrderController geOrderController() {
        OrderController orderController = new OrderController(new InMemoryRepository<>());
        return orderController;
    }

    private DishController geDishController() {
        DishController dishController = new DishController(new InMemoryRepository<>());
        return dishController;
    }

    @BeforeEach
    void setUp() {
        getMenuOfRestaurantCommand = new GetMenuOfRestaurantCommand();
        orderController = geOrderController();
        dishController = geDishController();
    }

    @Test
    void testGetName() {
        assertThat(getMenuOfRestaurantCommand.getName()).isEqualTo("get-menu-of-restaurant");
    }

    @Test
    void testGetCommandData() {
        assertThat(getMenuOfRestaurantCommand.getCommandData().getDescription())
                .isEqualTo("Tell the bot which restaurant's menu you want to look at");
    }

}
