package com.travelapp.service;


import com.travelapp.model.RateTour;

import java.util.List;
import java.util.Optional;

/**
 * Service Interface for managing RateTour.
 */
public interface RateTourService {

    RateTour save(RateTour rateTourDTO);

    /**
     * Get all the rateTours.
     *
     * @return the list of entities
     */
    List<RateTour> findAll();


    /**
     * Get the "id" rateTour.
     *
     * @param id the id of the entity
     * @return the entity
     */
    Optional<RateTour> findOne(Long id);

    /**
     * Delete the "id" rateTour.
     *
     * @param id the id of the entity
     */
    void delete(Long id);
}
