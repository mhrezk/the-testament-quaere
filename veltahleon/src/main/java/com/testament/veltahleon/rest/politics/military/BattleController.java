package com.testament.veltahleon.rest.politics.military;

import com.testament.veltahleon.model.entities.politics.military.Battle;
import com.testament.veltahleon.responses.CustomResponse;
import com.testament.veltahleon.services.ifc.politics.military.BattleService;
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
public class BattleController {

    @Autowired
    private BattleService battleService;

    @GetMapping("/battles")
    public ResponseEntity<CustomResponse> getPaginatedBattles(@RequestParam(value = "pageNumber", defaultValue = "0") int pageNumber, @RequestParam(value = "pageSize", defaultValue = "10") int pageSize) {
        List<Battle> battles = (List<Battle>) battleService.getBattlesWithPagination(pageNumber, pageSize);
        return ResponseEntity.ok(CustomResponse.builder()
                .timestamp(LocalDateTime.now())
                .status(HttpStatus.OK)
                .statusCode(HttpStatus.OK.value())
                .data(Map.of("paginatedBattles", battles))
                .message(battles.size() + " battles retrieved from page: " + (pageNumber + 1))
                .build()
        );
    }

    @GetMapping("/battles/all")
    public ResponseEntity<CustomResponse> getAllBattles() {
        List<Battle> battles = (List<Battle>) battleService.getBattles();
        return ResponseEntity.ok(CustomResponse.builder()
                .timestamp(LocalDateTime.now())
                .status(HttpStatus.OK)
                .statusCode(HttpStatus.OK.value())
                .data(Map.of("allBattles", battles))
                .message("All battles retrieved!")
                .build()
        );
    }

    @GetMapping("/battle/{id}")
    public ResponseEntity<CustomResponse> getBattleByID(@PathVariable Long id) {
        return ResponseEntity.ok(CustomResponse.builder()
                .timestamp(LocalDateTime.now())
                .status(HttpStatus.OK)
                .statusCode(HttpStatus.OK.value())
                .data(Map.of("queriedBattleByID", battleService.getBattleByID(id)))
                .message("Battle retrieved!")
                .build()
        );
    }

    @GetMapping("/battle/name")
    public ResponseEntity<CustomResponse> getBattleByBattleName(@RequestParam(value = "name") String name) {
        return ResponseEntity.ok(CustomResponse.builder()
                .timestamp(LocalDateTime.now())
                .status(HttpStatus.OK)
                .statusCode(HttpStatus.OK.value())
                .data(Map.of("queriedBattleByName", battleService.getBattleByName(name)))
                .message("Battle retrieved!")
                .build()
        );
    }

    @PostMapping("/save/battle")
    public ResponseEntity<CustomResponse> saveBattle(@RequestBody @Valid Battle battle) {
        return ResponseEntity.ok(CustomResponse.builder()
                .timestamp(LocalDateTime.now())
                .status(HttpStatus.OK)
                .statusCode(HttpStatus.OK.value())
                .data(Map.of("savedBattle", battleService.saveBattle(battle)))
                .message("Battle saved!")
                .build()
        );
    }

    @DeleteMapping("/delete/battle/{id}")
    public ResponseEntity<CustomResponse> deleteBattleByID(@PathVariable Long id) {
        return ResponseEntity.ok(CustomResponse.builder()
                .timestamp(LocalDateTime.now())
                .status(HttpStatus.OK)
                .statusCode(HttpStatus.OK.value())
                .data(Map.of("isBattleDeleted", battleService.deleteBattleByID(id)))
                .message("Battle deleted!")
                .build()
        );
    }

    @PatchMapping("/update/battle/{id}")
    public ResponseEntity<CustomResponse> updateBattle(@PathVariable Long id, @RequestBody @Valid Battle battle) {
        return ResponseEntity.ok(CustomResponse.builder()
                .timestamp(LocalDateTime.now())
                .status(HttpStatus.OK)
                .statusCode(HttpStatus.OK.value())
                .data(Map.of("updatedBattle", battleService.updateBattle(id, battle)))
                .message("Battle updated!")
                .build()
        );
    }
}
