package com.testament.veltahleon.rest.dogma;

import com.testament.veltahleon.dto.dogma.ReligionDTO;
import com.testament.veltahleon.mappers.dogma.ReligionMapper;
import com.testament.veltahleon.responses.CustomResponse;
import com.testament.veltahleon.model.entities.dogma.Religion;
import com.testament.veltahleon.services.ifc.dogma.ReligionService;
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
import java.util.List;
import java.util.Map;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/dogma")
@RequiredArgsConstructor
public class ReligionController {

    @Autowired
    private ReligionService religionService;

    @Autowired
    private ReligionMapper religionMapper;

    public final String IMAGE_PATH = "src/main/resources/assets/images/religions/";

    @GetMapping("/religions")
    public ResponseEntity<CustomResponse> getPaginatedReligions(@RequestParam(value = "pageNumber", defaultValue = "0") int pageNumber, @RequestParam(value = "pageSize", defaultValue = "10") int pageSize) {
        List<Religion> religions = (List<Religion>) religionService.getReligionsWithPagination((pageNumber - 1), pageSize);
        List<ReligionDTO> religionsDTO = religions.stream().map(r -> religionMapper.convertToDTO(r)).toList();
        return ResponseEntity.ok(CustomResponse.builder()
                .timestamp(LocalDateTime.now())
                .status(HttpStatus.OK)
                .statusCode(HttpStatus.OK.value())
                .data(Map.of("dataRetrieved", religionsDTO))
                .message(religions.size() + " religions retrieved from page: " + pageNumber)
                .build()
        );
    }

    @GetMapping("/religions/all")
    public ResponseEntity<CustomResponse> getAllReligions() {
        List<Religion> religions = (List<Religion>) religionService.getReligions();
        List<ReligionDTO> religionsDTO = religions.stream().map(r -> religionMapper.convertToDTO(r)).toList();
        return ResponseEntity.ok(CustomResponse.builder()
                .timestamp(LocalDateTime.now())
                .status(HttpStatus.OK)
                .statusCode(HttpStatus.OK.value())
                .data(Map.of("dataRetrieved", religionsDTO))
                .message("All religions retrieved!")
                .build()
        );
    }

    @GetMapping("/religions/all/count")
    public ResponseEntity<CustomResponse> getAllReligionsCount() {
        return ResponseEntity.ok(CustomResponse.builder()
                .timestamp(LocalDateTime.now())
                .status(HttpStatus.OK)
                .statusCode(HttpStatus.OK.value())
                .data(Map.of("datumRetrieved", religionService.countReligions()))
                .message("Count retrieved!")
                .build()
        );
    }

    @GetMapping("/religion/name")
    public ResponseEntity<CustomResponse> getReligionByName(@RequestParam(value = "name") String name) {
        ReligionDTO religionDTO = religionMapper.convertToDTO(religionService.getReligionByName(name));
        return ResponseEntity.ok(CustomResponse.builder()
                .timestamp(LocalDateTime.now())
                .status(HttpStatus.OK)
                .statusCode(HttpStatus.OK.value())
                .data(Map.of("datumRetrieved", religionDTO))
                .message("Religion retrieved!")
                .build()
        );
    }

    @GetMapping("/religion/{id}")
    public ResponseEntity<CustomResponse> getReligionByID(@PathVariable Long id) {
        ReligionDTO religionDTO = religionMapper.convertToDTO(religionService.getReligionByID(id));
        return ResponseEntity.ok(CustomResponse.builder()
                .timestamp(LocalDateTime.now())
                .status(HttpStatus.OK)
                .statusCode(HttpStatus.OK.value())
                .data(Map.of("datumRetrieved", religionDTO))
                .message("Religion retrieved!")
                .build()
        );
    }

    @GetMapping(path = "/religions/images/{imageName}", produces = MediaType.IMAGE_PNG_VALUE)
    public byte[] getReligiousImage(@PathVariable("imageName") String imageName) throws IOException {
        return Files.readAllBytes(Path.of(IMAGE_PATH + imageName));
    }

    @DeleteMapping("/delete/religion/{id}")
    public ResponseEntity<CustomResponse> deleteReligionByID(@PathVariable Long id) {
        return ResponseEntity.ok(CustomResponse.builder()
                .timestamp(LocalDateTime.now())
                .status(HttpStatus.OK)
                .statusCode(HttpStatus.OK.value())
                .data(Map.of("dataDeleted", religionService.deleteReligionByID(id)))
                .message("Religion deleted!")
                .build()
        );
    }

    @DeleteMapping("/delete/religion/{id}/{nationName}")
    public ResponseEntity<CustomResponse> deleteNationFromReligion(@PathVariable Long id, @PathVariable String nationName) {
        return ResponseEntity.ok(CustomResponse.builder()
                .timestamp(LocalDateTime.now())
                .status(HttpStatus.OK)
                .statusCode(HttpStatus.OK.value())
                .data(Map.of("dataUpdated", religionService.removeNationFromReligion(id, nationName)))
                .message("Nation deleted!")
                .build()
        );
    }

    @PostMapping("/save/religion")
    public ResponseEntity<CustomResponse> saveReligion(@RequestBody @Valid ReligionDTO religionDTO) {
        Religion religion = religionMapper.convertToEntity(religionDTO);
        return ResponseEntity.ok(CustomResponse.builder()
                .timestamp(LocalDateTime.now())
                .status(HttpStatus.OK)
                .statusCode(HttpStatus.OK.value())
                .data(Map.of("dataSaved", religionMapper.convertToDTO(religionService.saveReligion(religion))))
                .message("Religion saved!")
                .build()
        );
    }

    @PatchMapping("/update/religion/{id}")
    public ResponseEntity<CustomResponse> updateReligion(@PathVariable("id") Long id, @RequestBody @Valid ReligionDTO religionDTO) {
        Religion religion = religionMapper.convertToEntity(religionDTO);
        return ResponseEntity.ok(CustomResponse.builder()
                .timestamp(LocalDateTime.now())
                .status(HttpStatus.OK)
                .statusCode(HttpStatus.OK.value())
                .data(Map.of("dataUpdated", religionMapper.convertToDTO(religionService.updateReligion(id, religion))))
                .message("Religion updated!")
                .build()
        );
    }

    @PutMapping("/modify/religion/{id}")
    public ResponseEntity<CustomResponse> modifyReligion(@PathVariable("id") Long id, @RequestBody @Valid ReligionDTO religionDTO) {
        Religion religion = religionMapper.convertToEntity(religionDTO);
        return ResponseEntity.ok(CustomResponse.builder()
                .timestamp(LocalDateTime.now())
                .status(HttpStatus.OK)
                .statusCode(HttpStatus.OK.value())
                .data(Map.of("dataUpdated", religionMapper.convertToDTO(religionService.modifyReligion(id, religion))))
                .message("Religion updated!")
                .build()
        );
    }
}
