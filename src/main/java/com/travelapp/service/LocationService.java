package com.travelapp.service;


import com.travelapp.model.Location;

import java.util.List;
import java.util.Optional;

public interface LocationService {


    Location save(Location locationDTO);

    /**
     * Get all the locations.
     *
     * @return the list of entities
     */
    List<Location> findAll();


    /**
     * Get the "id" location.
     *
     * @param id the id of the entity
     * @return the entity
     */
    Optional<Location> findOne(Long id);

    /**
     * Delete the "id" location.
     *
     * @param id the id of the entity
     */
    void delete(Long id);
}
