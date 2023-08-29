package dev.c3rd.app.service.customer.impl;

import dev.c3rd.app.exception.customer.CustomerNotFoundException;
import dev.c3rd.app.model.customer.Customer;
import dev.c3rd.app.repository.CustomerRepository;
import dev.c3rd.app.service.customer.ICustomerService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerServiceImpl implements ICustomerService {

    private final CustomerRepository repository;

    public CustomerServiceImpl(CustomerRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<Customer> getAllCustomers() {
        return repository.findAll();
    }

    @Override
    public Customer saveCustomer(Customer customer) {
        return repository.save(customer);
    }

    @Override
    public Customer getCustomerById(Long id) {
        return repository.findById(id).orElseThrow(() -> new CustomerNotFoundException("Customer not found"));
    }

    @Override
    public Customer updateCustomer(Customer customer, Long id) {
        Customer existingCustomer = repository.findById(id).orElseThrow(() -> new CustomerNotFoundException("Customer not found"));

        existingCustomer.setFirstName(customer.getFirstName());
        existingCustomer.setLastName(customer.getLastName());
        existingCustomer.setEmail(customer.getEmail());

        repository.save(existingCustomer);

        return existingCustomer;
    }

    @Override
    public void deleteCustomer(Long id) {
        repository.findById(id).orElseThrow(() -> new CustomerNotFoundException("Customer not found"));
        repository.deleteById(id);
    }
}
