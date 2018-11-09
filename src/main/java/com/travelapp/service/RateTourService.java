package com.travelapp.service;


import com.travelapp.model.RateType;

import java.util.List;
import java.util.Optional;

/**
 * Service Interface for managing RateType.
 */
public interface RateTourService {

    RateType save(RateType rateTypeDTO);

    /**
     * Get all the rateTours.
     *
     * @return the list of entities
     */
    List<RateType> findAll();


    /**
     * Get the "id" rateTour.
     *
     * @param id the id of the entity
     * @return the entity
     */
    Optional<RateType> findOne(Long id);

    /**
     * Delete the "id" rateTour.
     *
     * @param id the id of the entity
     */
    void delete(Long id);
}
