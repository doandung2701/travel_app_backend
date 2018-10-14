package com.travelapp.controller;

import com.travelapp.controller.util.HeaderUtil;
import com.travelapp.exception.BadRequestException;
import com.travelapp.exception.ResourceNotFoundException;
import com.travelapp.model.User;
import com.travelapp.service.CustomerService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;

import java.util.List;
import java.util.Optional;

/**
 * REST controller for managing Customer.
 */
@RestController
@RequestMapping("/api")
public class CustomerResource {

    private final Logger log = LoggerFactory.getLogger(CustomerResource.class);

    private static final String ENTITY_NAME = "customer";

    private final CustomerService customerService;
    @Autowired
    public CustomerResource(CustomerService customerService) {
        this.customerService = customerService;
    }


    @PostMapping("/customers")
    public ResponseEntity<User> createCustomer(@Valid @RequestBody User customerDTO) throws URISyntaxException {
        log.debug("REST request to save Customer : {}", customerDTO);
        if (customerDTO.getId() != null) {
            throw new BadRequestException("A new customer cannot already have an ID");
        }
        User result = customerService.save(customerDTO);
        return ResponseEntity.created(new URI("/api/customers/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }


    @PutMapping("/customers")
    public ResponseEntity<User> updateCustomer(@Valid @RequestBody User customerDTO) throws URISyntaxException {
        log.debug("REST request to update Customer : {}", customerDTO);
        if (customerDTO.getId() == null) {
            throw new BadRequestException("Invalid id");
        }
        User result = customerService.save(customerDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, customerDTO.getId().toString()))
            .body(result);
    }

    @GetMapping("/customers")
    public List<User> getAllCustomers() {
        log.debug("REST request to get all Customers");
        return customerService.findAll();
    }


    @GetMapping("/customers/{id}")
    public ResponseEntity<User> getCustomer(@PathVariable Long id) {
        log.debug("REST request to get Customer : {}", id);
        Optional<User> customerDTO = customerService.findOne(id);
        if (customerDTO==null)
            throw new ResourceNotFoundException(ENTITY_NAME,"id",id);
        return ResponseEntity.ok(customerDTO.get());
    }


    @DeleteMapping("/customers/{id}")
    public ResponseEntity<Void> deleteCustomer(@PathVariable Long id) {
        log.debug("REST request to delete Customer : {}", id);
        customerService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
