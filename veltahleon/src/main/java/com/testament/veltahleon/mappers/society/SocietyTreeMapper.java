package com.testament.veltahleon.mappers.society;

import com.testament.veltahleon.dto.society.SocietyTreeDTO;
import com.testament.veltahleon.model.entities.society.Pedigree;
import com.testament.veltahleon.model.entities.society.SocietyTree;
import com.testament.veltahleon.services.ifc.society.PedigreeService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


@Component
@AllArgsConstructor
public class SocietyTreeMapper {

    @Autowired
    private PedigreeService pedigreeService;

    public SocietyTreeDTO convertToDTO(SocietyTree societyTree) {
        return SocietyTreeDTO.builder()
                .id(societyTree.getId())
                .treeName(societyTree.getTreeName())
                .pedigrees(societyTree.getPedigrees().stream().map(Pedigree::getLineageName).toList())
                .build();
    }

    public SocietyTree convertToEntity(SocietyTreeDTO societyTreeDTO) {
        SocietyTree s = new SocietyTree();
        s.setTreeName(societyTreeDTO.getTreeName());
        s.setPedigrees(societyTreeDTO.getPedigrees().stream().map(p -> pedigreeService.getPedigreeByLineageName(p)).toList());
        return s;
    }
}
