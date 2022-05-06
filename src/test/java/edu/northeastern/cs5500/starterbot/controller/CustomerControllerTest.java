package edu.northeastern.cs5500.starterbot.controller;

import static org.junit.Assert.*;

import edu.northeastern.cs5500.starterbot.model.Customer;
import edu.northeastern.cs5500.starterbot.repository.InMemoryRepository;
import org.junit.jupiter.api.Test;

class CustomerControllerTest {
    static final String customerName = "Joe";
    static final String customerName2 = "Joking";
    static final String phone = "123456789";
    static final String phone2 = "00000000000";
    static final String address = "123 23rd Place NE";
    static final String bankAccount = "4452 1234 4561 7894";

    private CustomerController getCustomerController() {
        CustomerController customerController = new CustomerController(new InMemoryRepository<>());
        return customerController;
    }

    @Test
    void testAddCustomer() {
        CustomerController customerController = getCustomerController();
        Customer customer =
                customerController.addCustomer(customerName, phone, address, bankAccount);
        assertEquals(customer.getCustomerName(), customerName);
        assertEquals(customer.getAddress(), address);
        assertEquals(customer.getPhone(), phone);
        assertEquals(customer.getBankAccount(), bankAccount);
    }

    @Test
    void TestGetSpecificCustomerBasedOnName() {
        CustomerController customerController = getCustomerController();
        customerController.addCustomer(customerName, phone, address, bankAccount);
        Customer customer =
                customerController.addCustomer(customerName, phone, address, bankAccount);

        // Search name that does not exists, return null
        assertEquals(customerController.getSpecificCustomerBasedOnName(customerName2), null);
        // Search name that exists, return that object
        String name =
                customerController.getSpecificCustomerBasedOnName(customerName).getCustomerName();
        assertEquals(name, customerName);
    }

    @Test
    void TestUpdateCustomer() {
        CustomerController customerController = getCustomerController();
        Customer customer =
                customerController.addCustomer(customerName, phone, address, bankAccount);
        customerController.updateCustomer(customerName, phone2, address, bankAccount);
        assertEquals(
                customerController.getSpecificCustomerBasedOnName(customerName).getPhone(), phone2);
    }
}
