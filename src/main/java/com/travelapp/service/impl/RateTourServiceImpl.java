package com.travelapp.service.impl;

import com.travelapp.model.RateTour;
import com.travelapp.repository.RateTourRepository;
import com.travelapp.service.RateTourService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Service Implementation for managing RateTour.
 */
@Service
@Transactional
public class RateTourServiceImpl implements RateTourService {

    private final Logger log = LoggerFactory.getLogger(RateTourServiceImpl.class);

    private final RateTourRepository rateTourRepository;

    @Autowired
    public RateTourServiceImpl(RateTourRepository rateTourRepository ) {
        this.rateTourRepository = rateTourRepository;
    }

    /**
     * Save a rateTour.
     *
     * @param rateTourDTO the entity to save
     * @return the persisted entity
     */
    @Override
    public RateTour save(RateTour rateTourDTO) {
        log.debug("Request to save RateTour : {}", rateTourDTO);

        rateTourDTO = rateTourRepository.save(rateTourDTO);
        return rateTourDTO;
    }

    /**
     * Get all the rateTours.
     *
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public List<RateTour> findAll() {
        log.debug("Request to get all RateTours");
        return rateTourRepository.findAll().stream()

            .collect(Collectors.toCollection(LinkedList::new));
    }


    /**
     * Get one rateTour by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<RateTour> findOne(Long id) {
        log.debug("Request to get RateTour : {}", id);
        return rateTourRepository.findById(id)
            ;
    }

    /**
     * Delete the rateTour by id.
     *
     * @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete RateTour : {}", id);
        rateTourRepository.deleteById(id);
    }
}
