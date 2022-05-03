package edu.northeastern.cs5500.starterbot.controller;

import edu.northeastern.cs5500.starterbot.model.Order;
import edu.northeastern.cs5500.starterbot.repository.GenericRepository;
import java.time.LocalDateTime;
import java.util.Collection;
import java.util.HashMap;
import javax.inject.Inject;

public class OrderController {

    GenericRepository<Order> orderRepository;

    @Inject
    OrderController(GenericRepository<Order> orderRepository) {
        this.orderRepository = orderRepository;
    }

    public void addOrder(
            int orderId,
            String customerId,
            String restaurantName,
            HashMap<String, Integer> itemsOrdered,
            double totalCharge) {
        Order order = new Order();
        order.setOrderId(orderId);
        order.setCustomerId(customerId);
        order.setRestaurantName(restaurantName);
        order.setItemsOrdered(itemsOrdered);
        order.setTotalCharge(totalCharge);
        order.setOrderCreatedTime(LocalDateTime.now());
        orderRepository.add(order);
    }

    // TODO: add order id to restaurant list(add one more method in restaurantController)
    public void addOrderToRestaurant(int orderId, String restaurantName) {}

    // TODO: get current time and compare it with order's creation time, true means order is
    // delivered
    // Should guaranteed that orderId is valid when passed in as parameter
    public boolean checkOrderStatus(int orderId) {
        LocalDateTime orderCreatedTime = null;
        Collection<Order> orders = orderRepository.getAll();
        for (Order order : orders) {
            if (order.getOrderId() == orderId) {
                orderCreatedTime = order.getOrderCreatedTime();
                break;
            }
        }

        // Assumes delivery time is 1min, it is a made-up time, just to show we have this function
        LocalDateTime deliverTime = orderCreatedTime.plusMinutes(1);
        LocalDateTime currentTime = LocalDateTime.now();

        if (currentTime.isAfter(deliverTime)) {
            return true;
        } else {
            return false;
        }
    }

    public int generateOrderId() {
        return (int) (orderRepository.count() + 1);
    }
}
