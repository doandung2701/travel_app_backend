package com.travelapp.service.impl;

import com.travelapp.model.Rate;
import com.travelapp.model.Tour;
import com.travelapp.repository.RateRepository;
import com.travelapp.repository.TourRepository;
import com.travelapp.service.RateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional
public class RateServiceImpl implements RateService {
    @Autowired
    private RateRepository repository;
    @Autowired
    private TourRepository tourRepository;
    @Override
    public Rate save(Rate rate) {
        return repository.save(rate);
    }

    @Override
    public List<Rate> findAll() {
        return repository.findAll();
    }

    @Override
    public Optional<Rate> findOne(Long id) {
        return repository.findById(id);
    }

    @Override
    public List<Rate> findRateByTour(Long tourId) {
        repository.findAllWithEagerRelationships().stream().filter(rate->rate.getTour().getId()==tourId).collect(Collectors.toList());
        return repository.findAll().stream().filter(rate->rate.getTour().getId()==tourId).collect(Collectors.toList());
    }

    @Override
    public void delete(Long id) {
        repository.deleteById(id);
    }
}
