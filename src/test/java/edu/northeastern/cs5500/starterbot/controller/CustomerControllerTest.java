package edu.northeastern.cs5500.starterbot.controller;

import static com.google.common.truth.Truth.assertThat;

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
        assertThat(customer.getCustomerName()).isEqualTo(customerName);
        assertThat(customer.getAddress()).isEqualTo(address);
        assertThat(customer.getPhone()).isEqualTo(phone);
        assertThat(customer.getBankAccount()).isEqualTo(bankAccount);
    }

    @Test
    void TestGetSpecificCustomerBasedOnName() {
        CustomerController customerController = getCustomerController();
        customerController.addCustomer(customerName, phone, address, bankAccount);
        Customer customer =
                customerController.addCustomer(customerName, phone, address, bankAccount);

        // Search name that does not exists, return null
        assertThat(customerController.getSpecificCustomerBasedOnName(customerName2)).isNull();
        // Search name that exists, return that object
        String name =
                customerController.getSpecificCustomerBasedOnName(customerName).getCustomerName();
        assertThat(name).isEqualTo(customerName);
    }

    @Test
    void TestUpdateCustomer() {
        CustomerController customerController = getCustomerController();
        Customer customer =
                customerController.addCustomer(customerName, phone, address, bankAccount);
        customerController.updateCustomer(customerName, phone2, address, bankAccount);
        assertThat(customerController.getSpecificCustomerBasedOnName(customerName).getPhone())
                .isEqualTo(phone2);
    }
}
