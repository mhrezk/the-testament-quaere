package com.testament.veltahleon.repositories.repo.ifc.places;

import com.testament.veltahleon.model.entities.history.library.Author;
import com.testament.veltahleon.model.entities.places.Province;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Collection;

public interface ProvinceRepository extends JpaRepository<Province, Long> {}
