package com.testament.veltahleon.services.ifc.society;

import com.testament.veltahleon.model.entities.society.SocietyTree;

import java.util.Collection;

public interface SocietyTreeService {

    Collection<SocietyTree> getSocietyTreesWithPagination(int pageNumber, int numberOfRecords);
    Collection<SocietyTree> getSocietyTrees();
    SocietyTree getSocietyTreeByID(Long id);
    SocietyTree getSocietyTreeByName(String treeName);
    SocietyTree getSocietyTreeByLineageName(String lineageName);
    Boolean deleteSocietyTreeByID(Long id);
    SocietyTree saveSocietyTree(SocietyTree societyTree);
    SocietyTree updateSocietyTree(Long id, SocietyTree societyTree);
    SocietyTree modifySocietyTree(Long id, SocietyTree societyTree);
}
