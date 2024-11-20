package com.testament.veltahleon.rest.dogma;

import com.testament.veltahleon.dto.dogma.ProphetDTO;
import com.testament.veltahleon.dto.history.LetterDTO;
import com.testament.veltahleon.mappers.dogma.ProphetMapper;
import com.testament.veltahleon.model.entities.history.Letter;
import com.testament.veltahleon.responses.CustomResponse;
import com.testament.veltahleon.model.entities.dogma.Prophet;
import com.testament.veltahleon.services.ifc.dogma.ProphetService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDateTime;
import java.util.Collection;
import java.util.List;
import java.util.Map;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/dogma")
@RequiredArgsConstructor
public class ProphetController {

    @Autowired
    private ProphetService prophetService;

    @Autowired
    private ProphetMapper prophetMapper;

    public final String IMAGE_PATH = "src/main/resources/assets/images/prophets/";

    @GetMapping("/prophets")
    public ResponseEntity<CustomResponse> getPaginatedProphets(@RequestParam(value = "pageNumber", defaultValue = "0") int pageNumber, @RequestParam(value = "pageSize", defaultValue = "10") int pageSize) {
        List<Prophet> prophets = (List<Prophet>) prophetService.getProphetsWithPagination((pageNumber - 1), pageSize);
        List<ProphetDTO> prophetsDTO = prophets.stream().map(p -> prophetMapper.convertToDTO(p)).toList();
        return ResponseEntity.ok(CustomResponse.builder()
                .timestamp(LocalDateTime.now())
                .status(HttpStatus.OK)
                .statusCode(HttpStatus.OK.value())
                .data(Map.of("dataRetrieved", prophetsDTO))
                .message(prophets.size() + " prophets retrieved from page: " + pageNumber)
                .build()
        );
    }

    @GetMapping("/prophets/religion/{religionName}")
    public ResponseEntity<CustomResponse> getPaginatedProphetsByReligionName(@PathVariable(value = "religionName") String religionName, @RequestParam(value = "pageNumber", defaultValue = "0") int pageNumber, @RequestParam(value = "pageSize", defaultValue = "10") int pageSize) {
        List<Prophet> prophets = (List<Prophet>) prophetService.getProphetsWithPaginationByReligionName(religionName, (pageNumber -1), pageSize);
        List<ProphetDTO> prophetsDTO = prophets.stream().map(p -> prophetMapper.convertToDTO(p)).toList();
        return ResponseEntity.ok(CustomResponse.builder()
                .timestamp(LocalDateTime.now())
                .status(HttpStatus.OK)
                .statusCode(HttpStatus.OK.value())
                .data(Map.of("dataRetrieved", prophetsDTO))
                .message(prophetsDTO.size() + " prophets retrieved from page: " + pageNumber)
                .build()
        );
    }

    @GetMapping("/prophets/sorted")
    public ResponseEntity<CustomResponse> getSortedPaginatedProphets(@RequestParam(value = "pageNumber", defaultValue = "0") int pageNumber, @RequestParam(value = "pageSize", defaultValue = "10") int pageSize) {
        List<Prophet> prophets = (List<Prophet>) prophetService.getProphetsSortedWithPagination((pageNumber - 1), pageSize);
        List<ProphetDTO> prophetsDTO = prophets.stream().map(p -> prophetMapper.convertToDTO(p)).toList();
        return ResponseEntity.ok(CustomResponse.builder()
                .timestamp(LocalDateTime.now())
                .status(HttpStatus.OK)
                .statusCode(HttpStatus.OK.value())
                .data(Map.of("dataRetrieved", prophetsDTO))
                .message(prophets.size() + " prophets retrieved from page: " + pageNumber)
                .build()
        );
    }

    @GetMapping("/prophets/all")
    public ResponseEntity<CustomResponse> getAllProphets() {
        List<Prophet> prophets = (List<Prophet>) prophetService.getProphets();
        return ResponseEntity.ok(CustomResponse.builder()
                .timestamp(LocalDateTime.now())
                .status(HttpStatus.OK)
                .statusCode(HttpStatus.OK.value())
                .data(Map.of("dataRetrieved", prophets))
                .message("All prophets retrieved!")
                .build()
        );
    }

    @GetMapping("/prophets/{religionName}/count")
    public ResponseEntity<CustomResponse> getAllProphetsByReligionNameCount(@PathVariable String religionName) {
        return ResponseEntity.ok(CustomResponse.builder()
                .timestamp(LocalDateTime.now())
                .status(HttpStatus.OK)
                .statusCode(HttpStatus.OK.value())
                .data(Map.of("dataRetrieved", prophetService.countProphetsByReligionName(religionName)))
                .message("All prophets of " + religionName + " retrieved!")
                .build()
        );
    }

    @GetMapping("/prophet/name")
    public ResponseEntity<CustomResponse> getProphetByName(@RequestParam(value = "name") String name) {
        ProphetDTO prophetDTO = prophetMapper.convertToDTO(prophetService.getProphetByName(name));
        return ResponseEntity.ok(CustomResponse.builder()
                .timestamp(LocalDateTime.now())
                .status(HttpStatus.OK)
                .statusCode(HttpStatus.OK.value())
                .data(Map.of("datumRetrieved", prophetDTO))
                .message("Prophet retrieved!")
                .build()
        );
    }

    @GetMapping("/prophet/{id}")
    public ResponseEntity<CustomResponse> getProphetByID(@PathVariable Long id) {
        ProphetDTO prophetDTO = prophetMapper.convertToDTO(prophetService.getProphetByID(id));
        return ResponseEntity.ok(CustomResponse.builder()
                .timestamp(LocalDateTime.now())
                .status(HttpStatus.OK)
                .statusCode(HttpStatus.OK.value())
                .data(Map.of("datumRetrieved", prophetDTO))
                .message("Prophet retrieved!")
                .build()
        );
    }

    @GetMapping(path = "/prophets/images/{imageName}", produces = MediaType.IMAGE_PNG_VALUE)
    public byte[] getProphetImage(@PathVariable("imageName") String imageName) throws IOException {
        return Files.readAllBytes(Path.of(IMAGE_PATH + imageName));
    }

    @DeleteMapping("/delete/prophet/{id}")
    public ResponseEntity<CustomResponse> deleteProphetByID(@PathVariable Long id) {
        return ResponseEntity.ok(CustomResponse.builder()
                .timestamp(LocalDateTime.now())
                .status(HttpStatus.OK)
                .statusCode(HttpStatus.OK.value())
                .data(Map.of("dataDeleted", prophetService.deleteProphetByID(id)))
                .message("Prophet deleted!")
                .build()
        );
    }

    @DeleteMapping("/delete/prophet/name/{name}")
    public ResponseEntity<CustomResponse> deleteProphetByName(@PathVariable String name) {
        return ResponseEntity.ok(CustomResponse.builder()
                .timestamp(LocalDateTime.now())
                .status(HttpStatus.OK)
                .statusCode(HttpStatus.OK.value())
                .data(Map.of("dataDeleted", prophetService.deleteProphetByName(name)))
                .message("Prophet deleted!")
                .build()
        );
    }

    @DeleteMapping("/delete/prophets")
    public ResponseEntity<CustomResponse> deleteAllProphets() {
        return ResponseEntity.ok(CustomResponse.builder()
                .timestamp(LocalDateTime.now())
                .status(HttpStatus.OK)
                .statusCode(HttpStatus.OK.value())
                .data(Map.of("dataDeleted", prophetService.deleteProphets()))
                .message("Prophets deleted!")
                .build()
        );
    }

    @PostMapping("/save/prophet/{religionName}")
    public ResponseEntity<CustomResponse> saveProphet(@PathVariable String religionName, @RequestBody @Valid ProphetDTO prophetDTO) {
        Prophet prophet = prophetMapper.convertToEntity(prophetDTO);
        return ResponseEntity.ok(CustomResponse.builder()
                .timestamp(LocalDateTime.now())
                .status(HttpStatus.OK)
                .statusCode(HttpStatus.OK.value())
                .data(Map.of("dataSaved", prophetMapper.convertToDTO(prophetService.saveProphet(prophet, religionName))))
                .message("Prophet saved!")
                .build()
        );
    }

    @PostMapping("/save/prophets")
    public ResponseEntity<CustomResponse> saveAllProphets(@RequestBody @Valid Collection<Prophet> prophets) {
        return ResponseEntity.ok(CustomResponse.builder()
                .timestamp(LocalDateTime.now())
                .status(HttpStatus.OK)
                .statusCode(HttpStatus.OK.value())
                .data(Map.of("dataSaved", prophetService.saveProphets(prophets)))
                .message("Prophets saved!")
                .build()
        );
    }

    @PatchMapping("/update/prophet/{id}")
    public ResponseEntity<CustomResponse> updateProphet(@PathVariable("id") Long id, @RequestBody @Valid ProphetDTO prophetDTO) {
        Prophet prophet = prophetMapper.convertToEntity(prophetDTO);
        return ResponseEntity.ok(CustomResponse.builder()
                .timestamp(LocalDateTime.now())
                .status(HttpStatus.OK)
                .statusCode(HttpStatus.OK.value())
                .data(Map.of("dataUpdated", prophetMapper.convertToDTO(prophetService.updateProphet(id, prophet))))
                .message("Prophet updated!")
                .build()
        );
    }

    @PutMapping("/modify/prophet/{id}")
    public ResponseEntity<CustomResponse> modifyProphet(@PathVariable("id") Long id, @RequestBody @Valid ProphetDTO prophetDTO) {
        Prophet prophet = prophetMapper.convertToEntity(prophetDTO);
        return ResponseEntity.ok(CustomResponse.builder()
                .timestamp(LocalDateTime.now())
                .status(HttpStatus.OK)
                .statusCode(HttpStatus.OK.value())
                .data(Map.of("dataUpdated", prophetMapper.convertToDTO(prophetService.modifyProphet(id, prophet))))
                .message("Prophet updated!")
                .build()
        );
    }
}
