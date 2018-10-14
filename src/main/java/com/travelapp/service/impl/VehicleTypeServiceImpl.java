package com.travelapp.service.impl;

import com.travelapp.model.VehicleType;
import com.travelapp.repository.VehicleTypeRepository;
import com.travelapp.service.VehicleTypeService;
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
 * Service Implementation for managing VehicleType.
 */
@Service
@Transactional
public class VehicleTypeServiceImpl implements VehicleTypeService {

    private final Logger log = LoggerFactory.getLogger(VehicleTypeServiceImpl.class);

    private  VehicleTypeRepository vehicleTypeRepository;

    @Autowired
    public VehicleTypeServiceImpl(VehicleTypeRepository vehicleTypeRepository ) {
        this.vehicleTypeRepository = vehicleTypeRepository;
    }


    @Override
    public VehicleType save(VehicleType vehicleType) {
        log.debug("Request to save VehicleType : {}", vehicleType);

        vehicleType = vehicleTypeRepository.save(vehicleType);
        return vehicleType;
    }

    /**
     * Get all the vehicleTypes.
     *
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public List<VehicleType> findAll() {
        log.debug("Request to get all VehicleTypes");
        return vehicleTypeRepository.findAll().stream()
            .collect(Collectors.toCollection(LinkedList::new));
    }


    @Override
    @Transactional(readOnly = true)
    public Optional<VehicleType> findOne(Long id) {
        log.debug("Request to get VehicleType : {}", id);
        return vehicleTypeRepository.findById(id);

    }

    /**
     * Delete the vehicleType by id.
     *
     * @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete VehicleType : {}", id);
        vehicleTypeRepository.deleteById(id);
    }
}
