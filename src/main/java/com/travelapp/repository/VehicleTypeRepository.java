package com.travelapp.repository;

import com.travelapp.model.VehicleType;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;
@Repository
public interface VehicleTypeRepository extends JpaRepository<VehicleType, Long> {
}
