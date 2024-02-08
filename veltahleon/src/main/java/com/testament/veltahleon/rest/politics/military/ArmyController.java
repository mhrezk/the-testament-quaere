package com.testament.veltahleon.rest.politics.military;

import com.testament.veltahleon.model.entities.politics.military.Army;
import com.testament.veltahleon.responses.CustomResponse;
import com.testament.veltahleon.services.ifc.politics.military.ArmyService;
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
public class ArmyController {

    @Autowired
    private ArmyService armyService;

    @GetMapping("/armies")
    public ResponseEntity<CustomResponse> getPaginatedArmies(@RequestParam(value = "pageNumber", defaultValue = "0") int pageNumber, @RequestParam(value = "pageSize", defaultValue = "10") int pageSize) {
        List<Army> armies = (List<Army>) armyService.getArmiesWithPagination(pageNumber, pageSize);
        return ResponseEntity.ok(CustomResponse.builder()
                .timestamp(LocalDateTime.now())
                .status(HttpStatus.OK)
                .statusCode(HttpStatus.OK.value())
                .data(Map.of("paginatedArmies", armies))
                .message(armies.size() + " armies retrieved from page: " + (pageNumber + 1))
                .build()
        );
    }

    @GetMapping("/armies/all")
    public ResponseEntity<CustomResponse> getAllArmies() {
        List<Army> armies = (List<Army>) armyService.getArmies();
        return ResponseEntity.ok(CustomResponse.builder()
                .timestamp(LocalDateTime.now())
                .status(HttpStatus.OK)
                .statusCode(HttpStatus.OK.value())
                .data(Map.of("allArmies", armies))
                .message("All armies retrieved!")
                .build()
        );
    }

    @GetMapping("/army/{id}")
    public ResponseEntity<CustomResponse> getArmyByID(@PathVariable Long id) {
        return ResponseEntity.ok(CustomResponse.builder()
                .timestamp(LocalDateTime.now())
                .status(HttpStatus.OK)
                .statusCode(HttpStatus.OK.value())
                .data(Map.of("queriedArmyByID", armyService.getArmyByID(id)))
                .message("Army retrieved!")
                .build()
        );
    }

    @GetMapping("/army/leader")
    public ResponseEntity<CustomResponse> getArmyByArmyName(@RequestParam(value = "name") String name) {
        return ResponseEntity.ok(CustomResponse.builder()
                .timestamp(LocalDateTime.now())
                .status(HttpStatus.OK)
                .statusCode(HttpStatus.OK.value())
                .data(Map.of("queriedArmyByName", armyService.getArmyByLeaderName(name)))
                .message("Army retrieved!")
                .build()
        );
    }

    @PostMapping("/save/army")
    public ResponseEntity<CustomResponse> saveArmy(@RequestBody @Valid Army army) {
        return ResponseEntity.ok(CustomResponse.builder()
                .timestamp(LocalDateTime.now())
                .status(HttpStatus.OK)
                .statusCode(HttpStatus.OK.value())
                .data(Map.of("savedArmy", armyService.saveArmy(army)))
                .message("Army saved!")
                .build()
        );
    }

    @DeleteMapping("/delete/army/{id}")
    public ResponseEntity<CustomResponse> deleteArmyByID(@PathVariable Long id) {
        return ResponseEntity.ok(CustomResponse.builder()
                .timestamp(LocalDateTime.now())
                .status(HttpStatus.OK)
                .statusCode(HttpStatus.OK.value())
                .data(Map.of("isArmyDeleted", armyService.deleteArmyByID(id)))
                .message("Army deleted!")
                .build()
        );
    }

    @PatchMapping("/update/army/{id}")
    public ResponseEntity<CustomResponse> updateArmy(@PathVariable Long id, @RequestBody @Valid Army army) {
        return ResponseEntity.ok(CustomResponse.builder()
                .timestamp(LocalDateTime.now())
                .status(HttpStatus.OK)
                .statusCode(HttpStatus.OK.value())
                .data(Map.of("updatedArmy", armyService.updateArmy(id, army)))
                .message("Army updated!")
                .build()
        );
    }
}
