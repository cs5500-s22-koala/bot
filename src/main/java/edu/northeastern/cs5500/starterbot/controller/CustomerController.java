package edu.northeastern.cs5500.starterbot.controller;

import edu.northeastern.cs5500.starterbot.model.Customer;
import edu.northeastern.cs5500.starterbot.repository.GenericRepository;
import java.util.Collection;
import javax.inject.Inject;

public class CustomerController {

    GenericRepository<Customer> customerRepository;

    @Inject
    CustomerController(GenericRepository<Customer> customerRepository) {
        this.customerRepository = customerRepository;
    }

    public void addCustomer(String customerName, String phone, String address, String bankAccount) {
        Customer customer = new Customer();
        customer.setCustomerName(customerName);
        customer.setPhone(phone);
        customer.setAddress(address);
        customer.setBankAccount(bankAccount);
        customerRepository.add(customer);
    }
    // If not found, return null
    public Customer getSpecificCustomerBasedOnName(String customerName) {
        Collection<Customer> customers = customerRepository.getAll();
        Customer result = null;
        for (Customer currentCustomer : customers) {
            if (currentCustomer.getCustomerName().equals(customerName)) {
                result = currentCustomer;
                break;
            }
        }
        return result;
    }

    // If updated return true
    public boolean updateCustomer(
            String customerName, String phone, String address, String bankAccount) {
        boolean foundFlag = false;
        Collection<Customer> customers = customerRepository.getAll();
        for (Customer customer : customers) {
            if (customer.getCustomerName().equals(customerName)) {
                customer.setPhone(phone);
                customer.setAddress(address);
                customer.setBankAccount(bankAccount);
                foundFlag = true;
                break;
            }
        }
        return foundFlag;
    }
}
