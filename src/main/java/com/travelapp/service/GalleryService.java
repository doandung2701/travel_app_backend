package com.travelapp.service;


import com.travelapp.model.Gallery;

import java.util.List;
import java.util.Optional;

/**
 * Service Interface for managing Gallery.
 */
public interface GalleryService {


    Gallery save(Gallery galleryDTO);

    /**
     * Get all the galleries.
     *
     * @return the list of entities
     */
    List<Gallery> findAll();


    /**
     * Get the "id" gallery.
     *
     * @param id the id of the entity
     * @return the entity
     */
    Optional<Gallery> findOne(Long id);

    /**
     * Delete the "id" gallery.
     *
     * @param id the id of the entity
     */
    void delete(Long id);
}
