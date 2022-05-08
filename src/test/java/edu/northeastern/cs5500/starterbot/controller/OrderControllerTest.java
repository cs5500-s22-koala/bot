package edu.northeastern.cs5500.starterbot.controller;

import static com.google.common.truth.Truth.assertThat;

import edu.northeastern.cs5500.starterbot.model.Order;
import edu.northeastern.cs5500.starterbot.repository.InMemoryRepository;
import java.time.LocalDateTime;
import java.util.HashMap;
import org.bson.types.ObjectId;
import org.junit.jupiter.api.Test;

public class OrderControllerTest {

    static final String customerId1 = "1";
    static final String customerId2 = "2";
    static final String restaurantName1 = "name1";
    static final double totcharge1 = 1.23;
    static final HashMap<String, Integer> itemsOrdered1 =
            new HashMap<>() {
                {
                    put("dish1", 1);
                    put("dish2", 2);
                }
            };

    private OrderController geOrderController() {
        OrderController orderController = new OrderController(new InMemoryRepository<>());
        return orderController;
    }

    @Test
    public void testGenerateOrderId() {
        OrderController orderController = geOrderController();
        int orderId = orderController.generateOrderId();
        assertThat(orderId).isEqualTo(1);
    }

    @Test
    public void testAddOrderId() {
        OrderController orderController = geOrderController();

        // before add an order
        assertThat(orderController.orderRepository.count()).isEqualTo(0);

        // add an order
        orderController.addOrder(
                orderController.generateOrderId(),
                customerId1,
                restaurantName1,
                itemsOrdered1,
                totcharge1);
        assertThat(orderController.orderRepository.count()).isEqualTo(1);

        // retrive objectId of order1
        ObjectId objId = null;
        Order order1 = null;
        for (Order order : orderController.orderRepository.getAll()) {
            if (order.getOrderId() == 1) {
                objId = order.getId();
                order1 = order;
                break;
            }
        }

        // update order1 customer Id
        order1.setCustomerId(customerId2);
        orderController.orderRepository.add(order1);
        assertThat(orderController.orderRepository.count()).isEqualTo(1);

        // delete an order from in memory repository
        orderController.orderRepository.delete(objId);
        assertThat(orderController.orderRepository.count()).isEqualTo(0);
    }

    @Test
    public void checkOrderStatus() {
        // order has not been delivered
        OrderController orderController = geOrderController();
        orderController.addOrder(
                orderController.generateOrderId(),
                customerId1,
                restaurantName1,
                itemsOrdered1,
                totcharge1);
        assertThat(orderController.checkOrderStatus(1)).isFalse();

        // order has been delivered
        for (Order order : orderController.orderRepository.getAll()) {
            if (order.getOrderId() == 1) {
                order.setOrderCreatedTime(LocalDateTime.of(2022, 5, 1, 18, 0, 0));
                break;
            }
        }
        assertThat(orderController.checkOrderStatus(1)).isTrue();
    }
}
