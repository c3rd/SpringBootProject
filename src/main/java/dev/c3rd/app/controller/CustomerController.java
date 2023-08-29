package dev.c3rd.app.controller;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

import dev.c3rd.app.model.customer.Customer;
import dev.c3rd.app.model.customer.CustomerModelAssembler;
import dev.c3rd.app.service.customer.ICustomerService;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.IanaLinkRelations;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/customer")
public class CustomerController {

    private final ICustomerService customerService;
    private final CustomerModelAssembler assembler;

    public CustomerController(ICustomerService customerService, CustomerModelAssembler assembler) {
        this.customerService = customerService;
        this.assembler = assembler;
    }

    @GetMapping
    public CollectionModel<EntityModel<Customer>> getAllCustomers() {

        List<EntityModel<Customer>> customers = customerService
                .getAllCustomers()
                .stream()
                .map(assembler::toModel)
                .collect(Collectors.toList());

        return CollectionModel.of(customers, linkTo(methodOn(CustomerController.class).getAllCustomers()).withSelfRel());
    }

    @GetMapping("{id}")
    public EntityModel<Customer> getCustomerById(@PathVariable Long id) {
        Customer customer = customerService.getCustomerById(id);

        return assembler.toModel(customer);
    }

    @PostMapping
    public ResponseEntity<?> saveCustomer(@RequestBody Customer customer) {

        EntityModel<Customer> newCustomer = assembler.toModel(customerService.saveCustomer(customer));

        return ResponseEntity.created(newCustomer.getRequiredLink(IanaLinkRelations.SELF).toUri()).body(newCustomer);

    }

    @PutMapping("{id}")
    public ResponseEntity<?> updateCustomer(@RequestBody Customer customer, @PathVariable Long id) {
        Customer existingCustomer = customerService.updateCustomer(customer, id);
        EntityModel<Customer> entityModel = assembler.toModel(existingCustomer);

        return ResponseEntity.created(entityModel.getRequiredLink(IanaLinkRelations.SELF).toUri()).body(entityModel);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<?> deleteCustomer(@PathVariable Long id) {
        customerService.deleteCustomer(id);

        return ResponseEntity.noContent().build();
    }
}
