package com.testament.veltahleon.repositories.society;

import com.testament.veltahleon.model.entities.society.SocietyTree;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SocietyTreeRepository extends JpaRepository<SocietyTree, Long> {

    SocietyTree findByPedigrees_SecondName(String familyName);
    SocietyTree findByPedigrees_LineageName(String lineageName);
    SocietyTree findByTreeName(String societalTreeName);
}
