package com.travelapp.service;


import com.travelapp.model.Hotel;

import java.util.List;
import java.util.Optional;

/**
 * Service Interface for managing Hotel.
 */
public interface HotelService {

    /**
     * Save a hotel.
     *
     * @param hotelDTO the entity to save
     * @return the persisted entity
     */
    Hotel save(Hotel hotelDTO);

    /**
     * Get all the hotels.
     *
     * @return the list of entities
     */
    List<Hotel> findAll();


    /**
     * Get the "id" hotel.
     *
     * @param id the id of the entity
     * @return the entity
     */
    Optional<Hotel> findOne(Long id);

    /**
     * Delete the "id" hotel.
     *
     * @param id the id of the entity
     */
    void delete(Long id);
}
