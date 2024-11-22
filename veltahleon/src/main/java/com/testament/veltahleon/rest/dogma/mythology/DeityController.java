package com.testament.veltahleon.rest.dogma.mythology;

import com.testament.veltahleon.dto.dogma.mythology.DeityDTO;
import com.testament.veltahleon.mappers.dogma.mythology.DeityMapper;
import com.testament.veltahleon.responses.CustomResponse;
import com.testament.veltahleon.model.entities.dogma.mythology.Deity;
import com.testament.veltahleon.services.ifc.dogma.mythology.DeityService;
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
public class DeityController {

    @Autowired
    private DeityService deityService;

    @Autowired
    private DeityMapper deityMapper;

    public final String IMAGE_PATH = "src/main/resources/assets/images/deities/";

    @GetMapping("/deities")
    public ResponseEntity<CustomResponse> getPaginatedDeities(@RequestParam(value = "pageNumber", defaultValue = "0") int pageNumber, @RequestParam(value = "pageSize", defaultValue = "10") int pageSize) {
        List<Deity> deities = (List<Deity>) deityService.getDeitiesWithPagination(pageNumber, pageSize);
        return ResponseEntity.ok(CustomResponse.builder()
                .timestamp(LocalDateTime.now())
                .status(HttpStatus.OK)
                .statusCode(HttpStatus.OK.value())
                .data(Map.of("dataRetrieved", deities))
                .message(deities.size() + " deities retrieved from page: " + (pageNumber + 1))
                .build()
        );
    }

    @GetMapping("/deities/religion/{religionName}")
    public ResponseEntity<CustomResponse> getPaginatedDeitiesByReligionName(@PathVariable(value = "religionName") String religionName, @RequestParam(value = "pageNumber", defaultValue = "0") int pageNumber, @RequestParam(value = "pageSize", defaultValue = "10") int pageSize) {
        List<Deity> deities = (List<Deity>) deityService.getDeitiesWithPaginationByReligionName(religionName, (pageNumber -1), pageSize);
        List<DeityDTO> deitiesDTO = deities.stream().map(d -> deityMapper.convertToDTO(d)).toList();
        return ResponseEntity.ok(CustomResponse.builder()
                .timestamp(LocalDateTime.now())
                .status(HttpStatus.OK)
                .statusCode(HttpStatus.OK.value())
                .data(Map.of("dataRetrieved", deitiesDTO))
                .message(deitiesDTO.size() + " deities retrieved from page: " + pageNumber)
                .build()
        );
    }

    @GetMapping("/deities/all")
    public ResponseEntity<CustomResponse> getAllDeities() {
        List<Deity> deities = (List<Deity>) deityService.getDeities();
        return ResponseEntity.ok(CustomResponse.builder()
                .timestamp(LocalDateTime.now())
                .status(HttpStatus.OK)
                .statusCode(HttpStatus.OK.value())
                .data(Map.of("dataRetrieved", deities))
                .message("All deities retrieved!")
                .build()
        );
    }

    @GetMapping("/deity/name")
    public ResponseEntity<CustomResponse> getDeityByName(@RequestParam(value = "name") String name) {
        DeityDTO deityDTO = deityMapper.convertToDTO(deityService.getDeityByName(name));
        return ResponseEntity.ok(CustomResponse.builder()
                .timestamp(LocalDateTime.now())
                .status(HttpStatus.OK)
                .statusCode(HttpStatus.OK.value())
                .data(Map.of("datumRetrieved", deityDTO))
                .message("Deity retrieved!")
                .build()
        );
    }

    @GetMapping("/deity/{id}")
    public ResponseEntity<CustomResponse> getDeityByID(@PathVariable Long id) {
        DeityDTO deityDTO = deityMapper.convertToDTO(deityService.getDeityByID(id));
        return ResponseEntity.ok(CustomResponse.builder()
                .timestamp(LocalDateTime.now())
                .status(HttpStatus.OK)
                .statusCode(HttpStatus.OK.value())
                .data(Map.of("datumRetrieved", deityDTO))
                .message("Deity retrieved!")
                .build()
        );
    }

    @GetMapping("/deities/{religionName}/count")
    public ResponseEntity<CustomResponse> getAllDeitiesByReligionNameCount(@PathVariable String religionName) {
        return ResponseEntity.ok(CustomResponse.builder()
                .timestamp(LocalDateTime.now())
                .status(HttpStatus.OK)
                .statusCode(HttpStatus.OK.value())
                .data(Map.of("datumRetrieved", deityService.countDeitiesByReligionName(religionName)))
                .message("All deities of " + religionName + " retrieved!")
                .build()
        );
    }

    @GetMapping(path = "/mythology/deities/images/{imageName}", produces = MediaType.IMAGE_PNG_VALUE)
    public byte[] getDeityImage(@PathVariable("imageName") String imageName) throws IOException {
        return Files.readAllBytes(Path.of(IMAGE_PATH + imageName));
    }

    @DeleteMapping("/delete/deity/{id}")
    public ResponseEntity<CustomResponse> deleteDeityByID(@PathVariable Long id) {
        return ResponseEntity.ok(CustomResponse.builder()
                .timestamp(LocalDateTime.now())
                .status(HttpStatus.OK)
                .statusCode(HttpStatus.OK.value())
                .data(Map.of("dataDeleted", deityService.deleteDeityByID(id)))
                .message("Deity deleted!")
                .build()
        );
    }

    @PostMapping("/save/deity/{religionName}")
    public ResponseEntity<CustomResponse> saveDeity(@PathVariable String religionName, @RequestBody @Valid DeityDTO deityDTO) {
        Deity deity = deityMapper.convertToEntity(religionName, deityDTO);
        return ResponseEntity.ok(CustomResponse.builder()
                .timestamp(LocalDateTime.now())
                .status(HttpStatus.OK)
                .statusCode(HttpStatus.OK.value())
                .data(Map.of("dataSaved", deityMapper.convertToDTO(deityService.saveDeity(deity, religionName))))
                .message("Deity saved!")
                .build()
        );
    }

    @PatchMapping("/update/deity/{id}")
    public ResponseEntity<CustomResponse> updateDeity(@PathVariable("id") Long id, @RequestBody @Valid DeityDTO deityDTO) {
        Deity deity = deityMapper.convertToEntity(deityDTO.getReligion(), deityDTO);
        return ResponseEntity.ok(CustomResponse.builder()
                .timestamp(LocalDateTime.now())
                .status(HttpStatus.OK)
                .statusCode(HttpStatus.OK.value())
                .data(Map.of("dataUpdated", deityMapper.convertToDTO(deityService.updateDeity(id, deity))))
                .message("Deity updated!")
                .build()
        );
    }

    @PutMapping("/modify/deity/{id}")
    public ResponseEntity<CustomResponse> modifyDeity(@PathVariable("id") Long id, @RequestBody @Valid DeityDTO deityDTO) {
        Deity deity = deityMapper.convertToEntity(deityDTO.getReligion(), deityDTO);
        return ResponseEntity.ok(CustomResponse.builder()
                .timestamp(LocalDateTime.now())
                .status(HttpStatus.OK)
                .statusCode(HttpStatus.OK.value())
                .data(Map.of("dataUpdated", deityMapper.convertToDTO(deityService.modifyDeity(id, deity))))
                .message("Deity updated!")
                .build()
        );
    }
}
