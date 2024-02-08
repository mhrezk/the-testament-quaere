package com.testament.veltahleon.services.ifc.politics.military;

import com.testament.veltahleon.model.entities.politics.military.Battalion;

import java.util.Collection;

public interface BattalionService {

    Collection<Battalion> getBattalionsWithPagination(int pageNumber, int numberOfRecords);
    Collection<Battalion> getBattalions();
    Battalion getBattalionByID(Long id);
    Battalion getBattalionByName(String name);
    Battalion getBattalionByLeaderName(String name);
    Boolean deleteBattalionByID(Long id);
    Battalion saveBattalion(Battalion battalion);
    Battalion updateBattalion(Long id, Battalion battalion);
}
