package com.travelapp.service.impl;

import com.travelapp.model.Hotel;
import com.travelapp.repository.HotelRepository;
import com.travelapp.service.HotelService;
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
 * Service Implementation for managing Hotel.
 */
@Service
@Transactional
public class HotelServiceImpl implements HotelService {

    private final Logger log = LoggerFactory.getLogger(HotelServiceImpl.class);

    private final HotelRepository hotelRepository;

    @Autowired
    public HotelServiceImpl(HotelRepository hotelRepository ) {
        this.hotelRepository = hotelRepository;
    }


    @Override
    public Hotel save(Hotel hotelDTO) {
        log.debug("Request to save Hotel : {}", hotelDTO);

        hotelDTO = hotelRepository.save(hotelDTO);
        return hotelDTO;
    }

    /**
     * Get all the hotels.
     *
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public List<Hotel> findAll() {
        log.debug("Request to get all Hotels");
        return hotelRepository.findAll().stream()

            .collect(Collectors.toCollection(LinkedList::new));
    }


    /**
     * Get one hotel by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<Hotel> findOne(Long id) {
        log.debug("Request to get Hotel : {}", id);
        return hotelRepository.findById(id)
            ;
    }

    /**
     * Delete the hotel by id.
     *
     * @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete Hotel : {}", id);
        hotelRepository.deleteById(id);
    }
}
