package com.travelapp.repository;

import com.travelapp.model.Rate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface RateRepository extends JpaRepository<Rate,Long> {
    @Query(value = "select distinct rate from Rate rate left join fetch rate.rateType")
    List<Rate> findAllWithEagerRelationships();

}
