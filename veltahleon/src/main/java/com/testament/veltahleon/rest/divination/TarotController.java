package com.testament.veltahleon.rest.divination;

import com.testament.veltahleon.responses.CustomResponse;
import com.testament.veltahleon.model.entities.divination.Tarot;
import com.testament.veltahleon.services.ifc.divination.TarotService;
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
@RequestMapping("/divination")
@RequiredArgsConstructor
public class TarotController {

    @Autowired
    private TarotService tarotService;

    @GetMapping("/tarots")
    public ResponseEntity<CustomResponse> getPaginatedTarots(@RequestParam(value = "pageNumber", defaultValue = "0") int pageNumber, @RequestParam(value = "pageSize", defaultValue = "10") int pageSize) {
        List<Tarot> tarots = (List<Tarot>) tarotService.getTarotsWithPagination(pageNumber, pageSize);
        return ResponseEntity.ok(CustomResponse.builder()
                .timestamp(LocalDateTime.now())
                .status(HttpStatus.OK)
                .statusCode(HttpStatus.OK.value())
                .data(Map.of("paginatedTarots", tarots))
                .message(tarots.size() + " tarots retrieved from page: " + (pageNumber + 1))
                .build()
        );
    }

    @GetMapping("/tarots/all")
    public ResponseEntity<CustomResponse> getAllTarots() {
        List<Tarot> tarots = (List<Tarot>) tarotService.getTarots();
        return ResponseEntity.ok(CustomResponse.builder()
                .timestamp(LocalDateTime.now())
                .status(HttpStatus.OK)
                .statusCode(HttpStatus.OK.value())
                .data(Map.of("allTarots", tarots))
                .message("All tarots retrieved!")
                .build()
        );
    }

    @GetMapping("/tarot/{id}")
    public ResponseEntity<CustomResponse> getTarotByID(@PathVariable Long id) {
        return ResponseEntity.ok(CustomResponse.builder()
                .timestamp(LocalDateTime.now())
                .status(HttpStatus.OK)
                .statusCode(HttpStatus.OK.value())
                .data(Map.of("queriedTarotByID", tarotService.getTarotByID(id)))
                .message("Tarot retrieved!")
                .build()
        );
    }

    @DeleteMapping("/delete/tarot/{id}")
    public ResponseEntity<CustomResponse> deleteTarotByID(@PathVariable Long id) {
        return ResponseEntity.ok(CustomResponse.builder()
                .timestamp(LocalDateTime.now())
                .status(HttpStatus.OK)
                .statusCode(HttpStatus.OK.value())
                .data(Map.of("isTarotDeleted", tarotService.deleteTarotByID(id)))
                .message("Tarot deleted!")
                .build()
        );
    }

    @PostMapping("/save/tarot")
    public ResponseEntity<CustomResponse> saveTarot(@RequestBody @Valid Tarot Tarot) {
        return ResponseEntity.ok(CustomResponse.builder()
                .timestamp(LocalDateTime.now())
                .status(HttpStatus.OK)
                .statusCode(HttpStatus.OK.value())
                .data(Map.of("savedTarot", tarotService.saveTarot(Tarot)))
                .message("Tarot saved!")
                .build()
        );
    }

    @PatchMapping("/update/tarot/{id}")
    public ResponseEntity<CustomResponse> updateTarot(@PathVariable("id") Long id, @RequestBody Tarot Tarot) {
        return ResponseEntity.ok(CustomResponse.builder()
                .timestamp(LocalDateTime.now())
                .status(HttpStatus.OK)
                .statusCode(HttpStatus.OK.value())
                .data(Map.of("updatedTarot", tarotService.updateTarot(id, Tarot)))
                .message("Tarot updated!")
                .build()
        );
    }
}
