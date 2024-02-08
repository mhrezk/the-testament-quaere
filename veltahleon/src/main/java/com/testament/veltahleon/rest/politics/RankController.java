package com.testament.veltahleon.rest.politics;

import com.testament.veltahleon.model.entities.politics.Rank;
import com.testament.veltahleon.responses.CustomResponse;
import com.testament.veltahleon.services.ifc.politics.RankService;
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
@RequestMapping("/politics")
@RequiredArgsConstructor
public class RankController {

    @Autowired
    private RankService rankService;

    @GetMapping("/ranks/all")
    public ResponseEntity<CustomResponse> getAllRanks() {
        List<Rank> ranks = (List<Rank>) rankService.getRanks();
        return ResponseEntity.ok(CustomResponse.builder()
                .timestamp(LocalDateTime.now())
                .status(HttpStatus.OK)
                .statusCode(HttpStatus.OK.value())
                .data(Map.of("allRanks", ranks))
                .message("All ranks retrieved!")
                .build()
        );
    }

    @GetMapping("/rank/{id}")
    public ResponseEntity<CustomResponse> getRankByID(@PathVariable Long id) {
        return ResponseEntity.ok(CustomResponse.builder()
                .timestamp(LocalDateTime.now())
                .status(HttpStatus.OK)
                .statusCode(HttpStatus.OK.value())
                .data(Map.of("queriedRankByID", rankService.getRankByID(id)))
                .message("Rank retrieved!")
                .build()
        );
    }

    @GetMapping("/rank/name")
    public ResponseEntity<CustomResponse> getRankByRankName(@RequestParam(value = "rankName") String rankName) {
        return ResponseEntity.ok(CustomResponse.builder()
                .timestamp(LocalDateTime.now())
                .status(HttpStatus.OK)
                .statusCode(HttpStatus.OK.value())
                .data(Map.of("queriedRankByName", rankService.getRankByRankName(rankName)))
                .message("Rank retrieved!")
                .build()
        );
    }

    @PostMapping("/save/rank")
    public ResponseEntity<CustomResponse> saveRank(@RequestBody @Valid Rank rank) {
        return ResponseEntity.ok(CustomResponse.builder()
                .timestamp(LocalDateTime.now())
                .status(HttpStatus.OK)
                .statusCode(HttpStatus.OK.value())
                .data(Map.of("savedRank", rankService.saveRank(rank)))
                .message("Rank saved!")
                .build()
        );
    }

    @DeleteMapping("/delete/rank/{id}")
    public ResponseEntity<CustomResponse> deleteRankByID(@PathVariable Long id) {
        return ResponseEntity.ok(CustomResponse.builder()
                .timestamp(LocalDateTime.now())
                .status(HttpStatus.OK)
                .statusCode(HttpStatus.OK.value())
                .data(Map.of("isRankDeleted", rankService.deleteRankByID(id)))
                .message("Rank deleted!")
                .build()
        );
    }

    @PatchMapping("/update/rank/{id}")
    public ResponseEntity<CustomResponse> updateRank(@PathVariable Long id, @RequestBody @Valid Rank rank) {
        return ResponseEntity.ok(CustomResponse.builder()
                .timestamp(LocalDateTime.now())
                .status(HttpStatus.OK)
                .statusCode(HttpStatus.OK.value())
                .data(Map.of("updatedRank", rankService.updateRank(id, rank)))
                .message("Rank updated!")
                .build()
        );
    }
}