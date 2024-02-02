package com.testament.veltahleon.repositories.repo.spring.boot.data.jpa.repository.ifc.politics.military;

import com.testament.veltahleon.model.entities.history.library.Author;
import com.testament.veltahleon.model.entities.politics.military.Unit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Collection;

@Repository
public interface UnitRepository extends JpaRepository<Unit, Long> {

    Unit findByUnitType(String unit);
}
