package dev.c3rd.app.service.customer;

import dev.c3rd.app.model.customer.Customer;

import java.util.List;

public interface ICustomerService {

    List<Customer> getAllCustomers();
    Customer saveCustomer(Customer customer);
    Customer getCustomerById(Long id);
    Customer updateCustomer(Customer customer, Long id);
    void deleteCustomer(Long id);

}
