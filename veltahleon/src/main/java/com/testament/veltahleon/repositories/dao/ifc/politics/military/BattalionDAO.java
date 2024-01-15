package com.testament.veltahleon.repositories.dao.ifc.politics.military;

import com.testament.veltahleon.model.entities.politics.military.Battalion;

import java.util.Collection;

public interface BattalionDAO {

    Collection<Battalion> getBattalions();
    Battalion getBattalionByID(Long id);
    Boolean deleteBattalionByID(Long id);
    Battalion saveBattalion(Battalion battalion);
    Battalion updateBattalion(Battalion battalion);
}
