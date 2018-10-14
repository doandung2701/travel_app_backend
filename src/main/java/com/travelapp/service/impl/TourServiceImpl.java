package com.travelapp.service.impl;

import com.travelapp.model.Tour;
import com.travelapp.repository.TourRepository;
import com.travelapp.service.TourService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing Tour.
 */
@Service
@Transactional
public class TourServiceImpl implements TourService {

    private final Logger log = LoggerFactory.getLogger(TourServiceImpl.class);

    private final TourRepository tourRepository;

    @Autowired
    public TourServiceImpl(TourRepository tourRepository ) {
        this.tourRepository = tourRepository;
    }

    /**
     * Save a tour.
     *
     * @param tourDTO the entity to save
     * @return the persisted entity
     */
    @Override
    public Tour save(Tour tourDTO) {
        log.debug("Request to save Tour : {}", tourDTO);

        tourDTO = tourRepository.save(tourDTO);
        return tourDTO;
    }

    /**
     * Get all the tours.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public Page<Tour> findAll(Pageable pageable) {
        log.debug("Request to get all Tours");
        return tourRepository.findAll(pageable)
         ;
    }

    /**
     * Get all the Tour with eager load of many-to-many relationships.
     *
     * @return the list of entities
     */
    public Page<Tour> findAllWithEagerRelationships(Pageable pageable) {
        return tourRepository.findAllWithEagerRelationships(pageable);
    }
    

    /**
     * Get one tour by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<Tour> findOne(Long id) {
        log.debug("Request to get Tour : {}", id);
        return tourRepository.findOneWithEagerRelationships(id)
            ;
    }

    /**
     * Delete the tour by id.
     *
     * @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete Tour : {}", id);
        tourRepository.deleteById(id);
    }
}
