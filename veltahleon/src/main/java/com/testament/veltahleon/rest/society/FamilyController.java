package com.testament.veltahleon.rest.society;

import com.testament.veltahleon.dto.society.FamilyDTO;
import com.testament.veltahleon.mappers.society.FamilyMapper;
import com.testament.veltahleon.model.entities.society.Family;
import com.testament.veltahleon.responses.CustomResponse;
import com.testament.veltahleon.services.ifc.society.FamilyService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.List;
import java.util.Map;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/society")
@RequiredArgsConstructor
public class FamilyController {

    @Autowired
    private FamilyService familyService;

    @Autowired
    private FamilyMapper familyMapper;

    @GetMapping("/families/{communityName}")
    public ResponseEntity<CustomResponse> getFamiliesByCommunityName(@PathVariable(value = "communityName") String communityName) {
        List<Family> families = (List<Family>) familyService.getFamiliesByCommunityName(communityName);
        List<FamilyDTO> familiesDTO = families.stream().map(p -> familyMapper.convertToDTO(p)).toList();
        return ResponseEntity.ok(CustomResponse.builder()
                .timestamp(LocalDateTime.now())
                .status(HttpStatus.OK)
                .statusCode(HttpStatus.OK.value())
                .data(Map.of("dataRetrieved", familiesDTO))
                .message("Family nodes under community of " + communityName + " retrieved!")
                .build()
        );
    }

    @GetMapping("/families/{communityName}/count")
    public ResponseEntity<CustomResponse> getFamiliesByCommunityNameCount(@PathVariable(value = "communityName") String communityName) {
        return ResponseEntity.ok(CustomResponse.builder()
                .timestamp(LocalDateTime.now())
                .status(HttpStatus.OK)
                .statusCode(HttpStatus.OK.value())
                .data(Map.of("datumRetrieved", familyService.countFamiliesByCommunityName(communityName)))
                .message("Node count for community " + communityName + " retrieved!")
                .build()
        );
    }

    @GetMapping("/family/{id}")
    public ResponseEntity<CustomResponse> getPersonByID(@PathVariable Long id) {
        return ResponseEntity.ok(CustomResponse.builder()
                .timestamp(LocalDateTime.now())
                .status(HttpStatus.OK)
                .statusCode(HttpStatus.OK.value())
                .data(Map.of("datumRetrieved", familyMapper.convertToDTO(familyService.getFamilyByID(id))))
                .message("Family node retrieved!")
                .build()
        );
    }

    @PostMapping("/save/families/{communityID}")
    public ResponseEntity<CustomResponse> saveFamilies(@RequestBody Collection<FamilyDTO> familyDTO, @PathVariable(value = "communityID") Long communityID, @RequestParam(value = "communitySize", defaultValue = "0") Integer communitySize) {
        List<Family> families = familyDTO.stream().map(f -> familyMapper.convertToEntity(f)).toList();
        return ResponseEntity.ok(CustomResponse.builder()
                .timestamp(LocalDateTime.now())
                .status(HttpStatus.OK)
                .statusCode(HttpStatus.OK.value())
                .data(Map.of("dataSaved", familyService.saveFamilies(families, communitySize, communityID).stream().map(f -> familyMapper.convertToDTO(f)).toList()))
                .message("Family nodes saved!")
                .build()
        );
    }

    @PostMapping("/save/family")
    public ResponseEntity<CustomResponse> saveFamily(@RequestBody FamilyDTO familyDTO) {
        Family family = familyMapper.convertToEntity(familyDTO);
        return ResponseEntity.ok(CustomResponse.builder()
                .timestamp(LocalDateTime.now())
                .status(HttpStatus.OK)
                .statusCode(HttpStatus.OK.value())
                .data(Map.of("dataSaved", familyMapper.convertToDTO(familyService.saveFamily(family))))
                .message("Family node saved!")
                .build()
        );
    }

    @DeleteMapping("/delete/family/{id}")
    public ResponseEntity<CustomResponse> deleteFamilyByID(@PathVariable Long id) {
        return ResponseEntity.ok(CustomResponse.builder()
                .timestamp(LocalDateTime.now())
                .status(HttpStatus.OK)
                .statusCode(HttpStatus.OK.value())
                .data(Map.of("dataDeleted", familyService.deleteFamilyByID(id)))
                .message("Family node deleted!")
                .build()
        );
    }

    @DeleteMapping("/delete/families")
    public ResponseEntity<CustomResponse> deleteAllFamilies() {
        return ResponseEntity.ok(CustomResponse.builder()
                .timestamp(LocalDateTime.now())
                .status(HttpStatus.OK)
                .statusCode(HttpStatus.OK.value())
                .data(Map.of("dataDeleted", familyService.deleteAllFamilies()))
                .message("All family nodes are deleted!")
                .build()
        );
    }

    @PatchMapping("/update/family/{id}")
    public ResponseEntity<CustomResponse> updateFamily(@PathVariable Long id, @RequestBody FamilyDTO familyDTO) {
        Family family = familyMapper.convertToEntity(familyDTO);
        return ResponseEntity.ok(CustomResponse.builder()
                .timestamp(LocalDateTime.now())
                .status(HttpStatus.OK)
                .statusCode(HttpStatus.OK.value())
                .data(Map.of("dataUpdated", familyMapper.convertToDTO(familyService.updateFamily(id, family))))
                .message("Family node updated!")
                .build()
        );
    }

    @PutMapping("/modify/family/{id}")
    public ResponseEntity<CustomResponse> modifyPerson(@PathVariable Long id, @RequestBody FamilyDTO familyDTO) {
        Family family = familyMapper.convertToEntity(familyDTO);return ResponseEntity.ok(CustomResponse.builder()
                .timestamp(LocalDateTime.now())
                .status(HttpStatus.OK)
                .statusCode(HttpStatus.OK.value())
                .data(Map.of("dataUpdated", familyMapper.convertToDTO(familyService.modifyFamily(id, family))))
                .message("Family node modified!")
                .build()
        );
    }
}
