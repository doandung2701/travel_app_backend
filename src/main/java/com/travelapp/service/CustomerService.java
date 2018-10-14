package com.travelapp.service;


import com.travelapp.model.User;

import java.util.List;
import java.util.Optional;

/**
 * Service Interface for managing Customer.
 */
public interface CustomerService {

    /**
     * Save a customer.
     *
     * @param customerDTO the entity to save
     * @return the persisted entity
     */
    User save(User customerDTO);

    /**
     * Get all the customers.
     *
     * @return the list of entities
     */
    List<User> findAll();


    /**
     * Get the "id" customer.
     *
     * @param id the id of the entity
     * @return the entity
     */
    Optional<User> findOne(Long id);

    /**
     * Delete the "id" customer.
     *
     * @param id the id of the entity
     */
    void delete(Long id);
}
