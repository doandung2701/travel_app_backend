package com.travelapp.service;


import com.travelapp.model.Provider;

import java.util.List;
import java.util.Optional;

/**
 * Service Interface for managing Provider.
 */
public interface ProviderService {


    Provider save(Provider providerDTO);

    /**
     * Get all the providers.
     *
     * @return the list of entities
     */
    List<Provider> findAll();


    /**
     * Get the "id" provider.
     *
     * @param id the id of the entity
     * @return the entity
     */
    Optional<Provider> findOne(Long id);

    /**
     * Delete the "id" provider.
     *
     * @param id the id of the entity
     */
    void delete(Long id);
}
