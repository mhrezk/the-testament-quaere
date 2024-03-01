package com.testament.veltahleon.rest.politics;

import com.testament.veltahleon.model.entities.politics.Vassal;
import com.testament.veltahleon.responses.CustomResponse;
import com.testament.veltahleon.services.ifc.politics.VassalService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/politics")
@RequiredArgsConstructor
public class VassalController {

    @Autowired
    private VassalService vassalService;

    @GetMapping("/vassals/all")
    public ResponseEntity<CustomResponse> getAllVassals() {
        List<Vassal> vassals = (List<Vassal>) vassalService.getVassals();
        return ResponseEntity.ok(CustomResponse.builder()
                .timestamp(LocalDateTime.now())
                .status(HttpStatus.OK)
                .statusCode(HttpStatus.OK.value())
                .data(Map.of("allVassals", vassals))
                .message("All vassals retrieved!")
                .build()
        );
    }

    @GetMapping("/vassal/{id}")
    public ResponseEntity<CustomResponse> getVassalByID(@PathVariable Long id) {
        return ResponseEntity.ok(CustomResponse.builder()
                .timestamp(LocalDateTime.now())
                .status(HttpStatus.OK)
                .statusCode(HttpStatus.OK.value())
                .data(Map.of("queriedVassalByID", vassalService.getVassalByID(id)))
                .message("Vassal retrieved!")
                .build()
        );
    }

    @PostMapping("/save/vassal")
    public ResponseEntity<CustomResponse> saveVassal(@RequestBody @Valid Vassal vassal) {
        return ResponseEntity.ok(CustomResponse.builder()
                .timestamp(LocalDateTime.now())
                .status(HttpStatus.OK)
                .statusCode(HttpStatus.OK.value())
                .data(Map.of("savedVassal", vassalService.saveVassal(vassal)))
                .message("Vassal saved!")
                .build()
        );
    }

    @DeleteMapping("/delete/vassal/{id}")
    public ResponseEntity<CustomResponse> deleteVassalByID(@PathVariable Long id) {
        return ResponseEntity.ok(CustomResponse.builder()
                .timestamp(LocalDateTime.now())
                .status(HttpStatus.OK)
                .statusCode(HttpStatus.OK.value())
                .data(Map.of("isVassalDeleted", vassalService.deleteVassalByID(id)))
                .message("Vassal deleted!")
                .build()
        );
    }

    @PatchMapping("/update/vassal/{id}")
    public ResponseEntity<CustomResponse> updateVassal(@PathVariable Long id, @RequestBody @Valid Vassal vassal) {
        return ResponseEntity.ok(CustomResponse.builder()
                .timestamp(LocalDateTime.now())
                .status(HttpStatus.OK)
                .statusCode(HttpStatus.OK.value())
                .data(Map.of("updatedVassal", vassalService.updateVassal(id, vassal)))
                .message("Vassal updated!")
                .build()
        );
    }
}
