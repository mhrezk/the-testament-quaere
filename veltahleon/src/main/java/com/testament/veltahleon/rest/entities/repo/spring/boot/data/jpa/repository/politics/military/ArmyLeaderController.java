package com.testament.veltahleon.rest.entities.repo.spring.boot.data.jpa.repository.politics.military;

import com.testament.veltahleon.model.entities.politics.military.ArmyLeader;
import com.testament.veltahleon.responses.CustomResponse;
import com.testament.veltahleon.services.entities.repo.ifc.politics.military.ArmyLeaderService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/politics/military")
@RequiredArgsConstructor
public class ArmyLeaderController {

    @Autowired
    private ArmyLeaderService armyLeaderService;

    @GetMapping("/armyLeaders")
    public ResponseEntity<CustomResponse> getPaginatedArmyLeaders(@RequestParam(value = "pageNumber", defaultValue = "0") int pageNumber, @RequestParam(value = "pageSize", defaultValue = "10") int pageSize) {
        List<ArmyLeader> armyLeaders = (List<ArmyLeader>) armyLeaderService.getArmyLeadersWithPagination(pageNumber, pageSize);
        return ResponseEntity.ok(CustomResponse.builder()
                .timestamp(LocalDateTime.now())
                .status(HttpStatus.OK)
                .statusCode(HttpStatus.OK.value())
                .data(Map.of("paginatedArmyLeaders", armyLeaders))
                .message(armyLeaders.size() + " army leaders retrieved from page: " + (pageNumber + 1))
                .build()
        );
    }

    @GetMapping("/armyLeaders/all")
    public ResponseEntity<CustomResponse> getAllArmyLeaders() {
        List<ArmyLeader> armyLeaders = (List<ArmyLeader>) armyLeaderService.getArmyLeaders();
        return ResponseEntity.ok(CustomResponse.builder()
                .timestamp(LocalDateTime.now())
                .status(HttpStatus.OK)
                .statusCode(HttpStatus.OK.value())
                .data(Map.of("allArmyLeaders", armyLeaders))
                .message("All army leaders retrieved!")
                .build()
        );
    }

    @GetMapping("/armyLeader/{id}")
    public ResponseEntity<CustomResponse> getArmyLeaderByID(@PathVariable Long id) {
        return ResponseEntity.ok(CustomResponse.builder()
                .timestamp(LocalDateTime.now())
                .status(HttpStatus.OK)
                .statusCode(HttpStatus.OK.value())
                .data(Map.of("queriedArmyLeaderByID", armyLeaderService.getArmyLeaderByID(id)))
                .message("Army leader retrieved!")
                .build()
        );
    }

    @GetMapping("/armyLeader/name")
    public ResponseEntity<CustomResponse> getArmyLeaderByArmyLeaderName(@RequestParam(value = "name") String name) {
        return ResponseEntity.ok(CustomResponse.builder()
                .timestamp(LocalDateTime.now())
                .status(HttpStatus.OK)
                .statusCode(HttpStatus.OK.value())
                .data(Map.of("queriedArmyLeaderByName", armyLeaderService.getArmyLeaderByName(name)))
                .message("Army leader retrieved!")
                .build()
        );
    }

    @PostMapping("/save/armyLeader")
    public ResponseEntity<CustomResponse> saveArmyLeader(@RequestBody @Valid ArmyLeader armyLeader) {
        return ResponseEntity.ok(CustomResponse.builder()
                .timestamp(LocalDateTime.now())
                .status(HttpStatus.OK)
                .statusCode(HttpStatus.OK.value())
                .data(Map.of("savedArmyLeader", armyLeaderService.saveArmyLeader(armyLeader)))
                .message("Army leader saved!")
                .build()
        );
    }

    @DeleteMapping("/delete/armyLeader/{id}")
    public ResponseEntity<CustomResponse> deleteArmyLeaderByID(@PathVariable Long id) {
        return ResponseEntity.ok(CustomResponse.builder()
                .timestamp(LocalDateTime.now())
                .status(HttpStatus.OK)
                .statusCode(HttpStatus.OK.value())
                .data(Map.of("isArmyLeaderDeleted", armyLeaderService.deleteArmyLeaderByID(id)))
                .message("Army leader deleted!")
                .build()
        );
    }

    @PatchMapping("/update/armyLeader/{id}")
    public ResponseEntity<CustomResponse> updateArmyLeader(@PathVariable Long id, @RequestBody @Valid ArmyLeader armyLeader) {
        return ResponseEntity.ok(CustomResponse.builder()
                .timestamp(LocalDateTime.now())
                .status(HttpStatus.OK)
                .statusCode(HttpStatus.OK.value())
                .data(Map.of("updatedArmyLeader", armyLeaderService.updateArmyLeader(id, armyLeader)))
                .message("Army leader updated!")
                .build()
        );
    }
}
