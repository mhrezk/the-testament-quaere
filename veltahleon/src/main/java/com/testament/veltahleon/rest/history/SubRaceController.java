package com.testament.veltahleon.rest.history;

import com.testament.veltahleon.dto.history.SubRaceDTO;
import com.testament.veltahleon.mappers.history.SubRaceMapper;
import com.testament.veltahleon.model.entities.history.SubRace;
import com.testament.veltahleon.responses.CustomResponse;
import com.testament.veltahleon.services.ifc.history.SubRaceService;
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
@RequestMapping("/history")
@RequiredArgsConstructor
public class SubRaceController {

    @Autowired
    private SubRaceService subRaceService;

    @Autowired
    private SubRaceMapper subRaceMapper;

    public final String imagePath = "src/main/resources/assets/images/sub_races/";

    @GetMapping("/subRaces")
    public ResponseEntity<CustomResponse> getPaginatedSubRaces(@RequestParam(value = "pageNumber", defaultValue = "0") int pageNumber, @RequestParam(value = "pageSize", defaultValue = "10") int pageSize) {
        List<SubRace> subRaces = (List< SubRace>) subRaceService.getSubRacesWithPagination((pageNumber - 1), pageSize);
        List<SubRaceDTO> subRacesDTO = subRaces.stream().map(s -> subRaceMapper.convertToDTO(s)).toList();
        return ResponseEntity.ok(CustomResponse.builder()
                .timestamp(LocalDateTime.now())
                .status(HttpStatus.OK)
                .statusCode(HttpStatus.OK.value())
                .data(Map.of("dataRetrieved", subRacesDTO))
                .message(subRacesDTO.size() + " sub-races retrieved from page: " + pageNumber)
                .build()
        );
    }

    @GetMapping("/subRaces/{raceName}/all")
    public ResponseEntity<CustomResponse> getPaginatedSubRacesByRaceName(@PathVariable(value = "raceName") String raceName, @RequestParam(value = "pageNumber", defaultValue = "0") int pageNumber, @RequestParam(value = "pageSize", defaultValue = "10") int pageSize) {
        List<SubRace> subRaces = (List< SubRace>) subRaceService.getSubRacesWithPaginationByRaceName(raceName, (pageNumber - 1), pageSize);
        List<SubRaceDTO> subRacesDTO = subRaces.stream().map(s -> subRaceMapper.convertToDTO(s)).toList();
        return ResponseEntity.ok(CustomResponse.builder()
                .timestamp(LocalDateTime.now())
                .status(HttpStatus.OK)
                .statusCode(HttpStatus.OK.value())
                .data(Map.of("dataRetrieved", subRacesDTO))
                .message(subRacesDTO.size() + " sub-races retrieved from page: " + pageNumber)
                .build()
        );
    }

    @GetMapping("/subRaces/all")
    public ResponseEntity<CustomResponse> getAllSubRaces() {
        List<SubRace> subRaces = (List<SubRace>) subRaceService.getSubRaces();
        List<SubRaceDTO> subRacesDTO = subRaces.stream().map(s -> subRaceMapper.convertToDTO(s)).toList();
        return ResponseEntity.ok(CustomResponse.builder()
                .timestamp(LocalDateTime.now())
                .status(HttpStatus.OK)
                .statusCode(HttpStatus.OK.value())
                .data(Map.of("dataRetrieved", subRacesDTO))
                .message("All sub-races retrieved!")
                .build()
        );
    }

    @GetMapping("/subRaces/{raceName}")
    public ResponseEntity<CustomResponse> getSubRacesByRaceName(@PathVariable(value = "raceName") String raceName) {
        List<SubRace> subRaces = (List<SubRace>) subRaceService.getSubRaces();
        List<SubRaceDTO> subRacesDTO = subRaces.stream().map(s -> subRaceMapper.convertToDTO(s)).toList();
        return ResponseEntity.ok(CustomResponse.builder()
                .timestamp(LocalDateTime.now())
                .status(HttpStatus.OK)
                .statusCode(HttpStatus.OK.value())
                .data(Map.of("dataRetrieved", subRacesDTO))
                .message("Sub-races of race " + raceName + " retrieved!")
                .build()
        );
    }

    @GetMapping("/subRaces/all/count")
    public ResponseEntity<CustomResponse> getAllSubRacesCount() {
        return ResponseEntity.ok(CustomResponse.builder()
                .timestamp(LocalDateTime.now())
                .status(HttpStatus.OK)
                .statusCode(HttpStatus.OK.value())
                .data(Map.of("datumRetrieved", subRaceService.countSubRaces()))
                .message("Count retrieved!")
                .build()
        );
    }

    @GetMapping("/subRaces/{raceName}/count")
    public ResponseEntity<CustomResponse> getSubRacesCountByRaceName(String raceName) {
        return ResponseEntity.ok(CustomResponse.builder()
                .timestamp(LocalDateTime.now())
                .status(HttpStatus.OK)
                .statusCode(HttpStatus.OK.value())
                .data(Map.of("datumRetrieved", subRaceService.countSubRaceByRaceName(raceName)))
                .message("Count retrieved!")
                .build()
        );
    }

    @GetMapping("/subRace/{id}")
    public ResponseEntity<CustomResponse> getSubRaceByID(@PathVariable Long id) {
        return ResponseEntity.ok(CustomResponse.builder()
                .timestamp(LocalDateTime.now())
                .status(HttpStatus.OK)
                .statusCode(HttpStatus.OK.value())
                .data(Map.of("datumRetrieved", subRaceMapper.convertToDTO(subRaceService.getSubRaceByID(id))))
                .message("Sub-race retrieved!")
                .build()
        );
    }

    @GetMapping("/subRace")
    public ResponseEntity<CustomResponse> getSubRaceByName(@RequestParam(value = "name") String name) {
        return ResponseEntity.ok(CustomResponse.builder()
                .timestamp(LocalDateTime.now())
                .status(HttpStatus.OK)
                .statusCode(HttpStatus.OK.value())
                .data(Map.of("datumRetrieved", subRaceService.getSubRaceByName(name)))
                .message("Sub-race retrieved!")
                .build()
        );
    }

    @GetMapping("/subRace/race")
    public ResponseEntity<CustomResponse> getSubRaceByRaceName(@RequestParam(value = "raceName") String raceName) {
        return ResponseEntity.ok(CustomResponse.builder()
                .timestamp(LocalDateTime.now())
                .status(HttpStatus.OK)
                .statusCode(HttpStatus.OK.value())
                .data(Map.of("datumRetrieved", subRaceService.getSubRacesByRaceName(raceName)))
                .message("Sub-races of " + raceName + " retrieved!")
                .build()
        );
    }

    @GetMapping("/subRace/{subRaceName}/exist")
    public ResponseEntity<CustomResponse> getSubRaceNameExistence(@PathVariable(value = "subRaceName") String subRaceName) {
        return ResponseEntity.ok(CustomResponse.builder()
                .timestamp(LocalDateTime.now())
                .status(HttpStatus.OK)
                .statusCode(HttpStatus.OK.value())
                .data(Map.of("datumRetrieved", subRaceService.doesSubRaceNameExist(subRaceName)))
                .message("Sub-race name exists!")
                .build()
        );
    }

    @GetMapping(path = "/subRaces/images/{imageName}", produces = MediaType.IMAGE_PNG_VALUE)
    public byte[] getSubRacialImage(@PathVariable("imageName") String imageName) throws IOException {
        return Files.readAllBytes(Path.of(imagePath + imageName));
    }

    @DeleteMapping("/delete/subRace/{id}")
    public ResponseEntity<CustomResponse> deleteSubRaceByID(@PathVariable Long id) {
        return ResponseEntity.ok(CustomResponse.builder()
                .timestamp(LocalDateTime.now())
                .status(HttpStatus.OK)
                .statusCode(HttpStatus.OK.value())
                .data(Map.of("dataDeleted", subRaceService.deleteSubRaceByID(id)))
                .message("Sub-race deleted!")
                .build()
        );
    }

    @PostMapping("/save/subRace/{raceName}")
    public ResponseEntity<CustomResponse> saveSubRace(@RequestBody @Valid SubRaceDTO subRaceDTO, @PathVariable(value = "raceName") String raceName) {
        SubRace subRace = subRaceMapper.convertToEntity(subRaceDTO, raceName);
        return ResponseEntity.ok(CustomResponse.builder()
                .timestamp(LocalDateTime.now())
                .status(HttpStatus.OK)
                .statusCode(HttpStatus.OK.value())
                .data(Map.of("dataSaved", subRaceMapper.convertToDTO(subRaceService.saveSubRace(subRace, raceName))))
                .message("Sub-race saved!")
                .build()
        );
    }

    @PatchMapping("/update/subRace/{id}/{raceName}")
    public ResponseEntity<CustomResponse> updateSubRace(@PathVariable("id") Long id, @PathVariable(value = "raceName") String raceName, @RequestBody @Valid SubRaceDTO subRaceDTO) {
        SubRace subRace = subRaceMapper.convertToEntity(subRaceDTO, raceName);
        return ResponseEntity.ok(CustomResponse.builder()
                .timestamp(LocalDateTime.now())
                .status(HttpStatus.OK)
                .statusCode(HttpStatus.OK.value())
                .data(Map.of("dataUpdated", subRaceService.updateSubRace(id, subRace)))
                .message("Sub-race updated!")
                .build()
        );
    }

    @PutMapping("/modify/subRace/{id}/{raceName}")
    public ResponseEntity<CustomResponse> modifySubRace(@PathVariable Long id, @PathVariable(value = "raceName") String raceName, @RequestBody SubRaceDTO subRaceDTO) {
        SubRace subRace = subRaceMapper.convertToEntity(subRaceDTO, raceName);
        return ResponseEntity.ok(CustomResponse.builder()
                .timestamp(LocalDateTime.now())
                .status(HttpStatus.OK)
                .statusCode(HttpStatus.OK.value())
                .data(Map.of("dataUpdated", subRaceMapper.convertToDTO(subRaceService.modifySubRace(id, subRace))))
                .message("Sub-race updated!")
                .build()
        );
    }
}
