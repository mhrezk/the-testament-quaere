package com.testament.veltahleon.repositories.places;

import com.testament.veltahleon.model.entities.places.Province;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Collection;

@Repository
public interface ProvinceRepository extends JpaRepository<Province, Long> {

    Province findByName(String name);
    Province findByCapital_Name(String name);
    //Collection<Province> findByNation_Name(String name);
    long countByName(String name);
}
