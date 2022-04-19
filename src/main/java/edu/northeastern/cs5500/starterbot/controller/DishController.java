package edu.northeastern.cs5500.starterbot.controller;

import edu.northeastern.cs5500.starterbot.model.Dish;
import edu.northeastern.cs5500.starterbot.repository.GenericRepository;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import javax.inject.Inject;

public class DishController {

    GenericRepository<Dish> dishRepository;

    @Inject
    DishController(GenericRepository<Dish> dishRepository) {
        this.dishRepository = dishRepository;
    }

    public void addDish(int dishId, String dishName, double price, String restaurantName) {
        Dish dish = new Dish();
        dish.setDishId(dishId);
        dish.setDishName(dishName);
        dish.setPrice(price);
        dish.setRestaurantName(restaurantName);
        dishRepository.add(dish);
    }

    public boolean updateDishPrice(int dishId, double newPrice) {
        boolean foundFlag = false;
        Collection<Dish> dishes = dishRepository.getAll();
        for (Dish dish : dishes) {
            if (dish.getDishId() == dishId) {
                dish.setPrice(newPrice);
                dishRepository.update(dish);
                foundFlag = true;
                break;
            }
        }
        return foundFlag;
    }

    public List<Dish> getMenuOfARestaurant(String restaurantName) {
        List<Dish> menu = new ArrayList<>();
        Collection<Dish> dishes = dishRepository.getAll();
        for (Dish dish : dishes) {
            if (dish.getRestaurantName().equals(restaurantName)) {
                menu.add(dish);
            }
        }
        return menu;
    }

    public Dish findADish(String dishName, String restaurantName) {
        Collection<Dish> dishes = dishRepository.getAll();
        for (Dish dish : dishes) {
            if (dish.getDishName().equals(dishName)
                    && dish.getRestaurantName().equals(restaurantName)) {
                return dish;
            }
        }
        return null;
    }
}
