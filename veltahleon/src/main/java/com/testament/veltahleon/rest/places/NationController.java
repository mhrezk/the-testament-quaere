package com.testament.veltahleon.rest.places;

import com.testament.veltahleon.dto.places.NationDTO;
import com.testament.veltahleon.mappers.places.NationMapper;
import com.testament.veltahleon.model.entities.places.Nation;
import com.testament.veltahleon.responses.CustomResponse;
import com.testament.veltahleon.services.ifc.places.NationService;
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
@RequestMapping("/places")
@RequiredArgsConstructor
public class NationController {

    @Autowired
    private NationService nationService;

    @Autowired
    private NationMapper nationMapper;

    //public final String IMAGE_PATH = "src/main/resources/assets/images/flags/";

    @GetMapping("/nations")
    public ResponseEntity<CustomResponse> getPaginatedNations(@RequestParam(value = "pageNumber", defaultValue = "0") int pageNumber, @RequestParam(value = "pageSize", defaultValue = "10") int pageSize) {
        List<Nation> nations = (List<Nation>) nationService.getNationsWithPagination((pageNumber - 1), pageSize);
        List<NationDTO> nationsDTO = nations.stream().map(n -> nationMapper.convertToDTO(n)).toList();
        return ResponseEntity.ok(CustomResponse.builder()
                .timestamp(LocalDateTime.now())
                .status(HttpStatus.OK)
                .statusCode(HttpStatus.OK.value())
                .data(Map.of("dataRetrieved", nationsDTO))
                .message(nations.size() + " nations retrieved from page: " + pageNumber)
                .build()
        );
    }

    @GetMapping("/nations/all")
    public ResponseEntity<CustomResponse> getAllNations() {
        List<Nation> nations = (List<Nation>) nationService.getNations();
        List<NationDTO> nationsDTO = nations.stream().map(n -> nationMapper.convertToDTO(n)).toList();
        return ResponseEntity.ok(CustomResponse.builder()
                .timestamp(LocalDateTime.now())
                .status(HttpStatus.OK)
                .statusCode(HttpStatus.OK.value())
                .data(Map.of("dataRetrieved", nationsDTO))
                .message("All nations retrieved!")
                .build()
        );
    }

    @GetMapping("/nations/all/count")
    public ResponseEntity<CustomResponse> getAllNationsCount() {
        return ResponseEntity.ok(CustomResponse.builder()
                .timestamp(LocalDateTime.now())
                .status(HttpStatus.OK)
                .statusCode(HttpStatus.OK.value())
                .data(Map.of("datumRetrieved", nationService.countNations()))
                .message("Count retrieved!")
                .build()
        );
    }

    @GetMapping("/nation/{id}")
    public ResponseEntity<CustomResponse> getNationByID(@PathVariable Long id) {
        Nation nation = nationService.getNationByID(id);
        NationDTO nationDTO = nationMapper.convertToDTO(nation);
        return ResponseEntity.ok(CustomResponse.builder()
                .timestamp(LocalDateTime.now())
                .status(HttpStatus.OK)
                .statusCode(HttpStatus.OK.value())
                .data(Map.of("datumRetrieved", nationDTO))
                .message("Nation retrieved!")
                .build()
        );
    }

    @GetMapping("/nation/name")
    public ResponseEntity<CustomResponse> getNationByNationName(@RequestParam(value = "nationName") String nationName) {
        Nation nation = nationService.getNationByName(nationName);
        NationDTO nationDTO = nationMapper.convertToDTO(nation);
        return ResponseEntity.ok(CustomResponse.builder()
                .timestamp(LocalDateTime.now())
                .status(HttpStatus.OK)
                .statusCode(HttpStatus.OK.value())
                .data(Map.of("datumRetrieved", nationDTO))
                .message("Nation retrieved!")
                .build()
        );
    }

    @PostMapping("/save/nation")
    public ResponseEntity<CustomResponse> saveNation(@RequestBody @Valid NationDTO nationDTO) {
        Nation nation = nationMapper.convertToEntity(nationDTO);
        return ResponseEntity.ok(CustomResponse.builder()
                .timestamp(LocalDateTime.now())
                .status(HttpStatus.OK)
                .statusCode(HttpStatus.OK.value())
                .data(Map.of("dataSaved", nationMapper.convertToDTO(nationService.saveNation(nation))))
                .message("Nation saved!")
                .build()
        );
    }

    @DeleteMapping("/delete/nation/{id}")
    public ResponseEntity<CustomResponse> deleteNationByID(@PathVariable Long id) {
        return ResponseEntity.ok(CustomResponse.builder()
                .timestamp(LocalDateTime.now())
                .status(HttpStatus.OK)
                .statusCode(HttpStatus.OK.value())
                .data(Map.of("dataDeleted", nationService.deleteNationByID(id)))
                .message("Nation deleted!")
                .build()
        );
    }

    @DeleteMapping("/delete/nation")
    public ResponseEntity<CustomResponse> deleteNationByName(@RequestParam(value = "nationName") String nationName) {
        return ResponseEntity.ok(CustomResponse.builder()
                .timestamp(LocalDateTime.now())
                .status(HttpStatus.OK)
                .statusCode(HttpStatus.OK.value())
                .data(Map.of("dataDeleted", nationService.deleteNationByName(nationName)))
                .message("Nation deleted!")
                .build()
        );
    }

    @PatchMapping("/update/nation/{id}")
    public ResponseEntity<CustomResponse> updateNation(@PathVariable Long id, @RequestBody @Valid NationDTO nationDTO) {
        Nation nation = nationMapper.convertToEntity(nationDTO);
        return ResponseEntity.ok(CustomResponse.builder()
                .timestamp(LocalDateTime.now())
                .status(HttpStatus.OK)
                .statusCode(HttpStatus.OK.value())
                .data(Map.of("dataUpdated", nationMapper.convertToDTO(nationService.updateNation(id, nation))))
                .message("Nation updated!")
                .build()
        );
    }

    @PutMapping("/modify/nation/{id}")
    public ResponseEntity<CustomResponse> modifyNation(@PathVariable Long id, @RequestBody @Valid NationDTO nationDTO) {
        Nation nation = nationMapper.convertToEntity(nationDTO);
        return ResponseEntity.ok(CustomResponse.builder()
                .timestamp(LocalDateTime.now())
                .status(HttpStatus.OK)
                .statusCode(HttpStatus.OK.value())
                .data(Map.of("dataUpdated", nationMapper.convertToDTO(nationService.modifyNation(id, nation))))
                .message("Nation updated!")
                .build()
        );
    }
}
