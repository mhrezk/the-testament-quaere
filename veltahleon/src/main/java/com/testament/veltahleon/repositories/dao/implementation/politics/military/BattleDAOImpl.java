package com.testament.veltahleon.repositories.dao.implementation.politics.military;

import com.testament.veltahleon.repositories.dao.ifc.politics.military.BattleDAO;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class BattleDAOImpl implements BattleDAO {

    @Autowired
    private EntityManager entityManager;

}
