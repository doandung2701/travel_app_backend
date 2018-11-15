package com.travelapp.repository;

import com.travelapp.model.Provider;
import com.travelapp.model.Tour;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

import java.util.List;


/**
 * Spring Data  repository for the Provider entity.
 */
@SuppressWarnings("unused")
@Repository
public interface ProviderRepository extends JpaRepository<Provider, Long> {
    Provider getProviderByName(String providerName);
}
