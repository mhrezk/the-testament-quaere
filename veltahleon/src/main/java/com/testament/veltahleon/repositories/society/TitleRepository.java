package com.testament.veltahleon.repositories.society;

import com.testament.veltahleon.model.entities.society.Title;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TitleRepository extends JpaRepository<Title, Long> {}
