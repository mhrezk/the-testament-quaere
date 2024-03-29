package com.testament.veltahleon.repositories.divination;

import com.testament.veltahleon.model.entities.calendar.Day;
import com.testament.veltahleon.model.entities.divination.Tarot;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Collection;

public interface TarotRepository extends JpaRepository<Tarot, Long> {
}
