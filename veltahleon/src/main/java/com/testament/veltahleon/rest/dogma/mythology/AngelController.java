package com.testament.veltahleon.rest.dogma.mythology;

import com.testament.veltahleon.dto.dogma.mythology.AngelDTO;
import com.testament.veltahleon.mappers.dogma.mythology.AngelMapper;
import com.testament.veltahleon.responses.CustomResponse;
import com.testament.veltahleon.model.entities.dogma.mythology.Angel;
import com.testament.veltahleon.services.ifc.dogma.mythology.AngelService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/dogma")
@RequiredArgsConstructor
public class AngelController {

    @Autowired
    private AngelService angelService;

    @Autowired
    private AngelMapper angelMapper;

    @GetMapping("/angels")
    public ResponseEntity<CustomResponse> getPaginatedAngels(@RequestParam(value = "pageNumber", defaultValue = "0") int pageNumber, @RequestParam(value = "pageSize", defaultValue = "10") int pageSize) {
        List<Angel> angels = (List<Angel>) angelService.getAngelsWithPagination((pageNumber - 1), pageSize);
        List<AngelDTO> angelsDTO = angels.stream().map(a -> angelMapper.convertToDTO(a)).toList();
        return ResponseEntity.ok(CustomResponse.builder()
                .timestamp(LocalDateTime.now())
                .status(HttpStatus.OK)
                .statusCode(HttpStatus.OK.value())
                .data(Map.of("dataRetrieved", angelsDTO))
                .message(angelsDTO.size() + " angels retrieved from page: " + pageNumber)
                .build()
        );
    }

    @GetMapping("/angels/religion/{religionName}")
    public ResponseEntity<CustomResponse> getPaginatedAngelsByReligionName(@PathVariable(value = "religionName") String religionName, @RequestParam(value = "pageNumber", defaultValue = "0") int pageNumber, @RequestParam(value = "pageSize", defaultValue = "5") int pageSize) {
        List<Angel> angels = (List<Angel>) angelService.getAngelsByReligionNameWithPagination((pageNumber - 1), pageSize, religionName);
        List<AngelDTO> angelsDTO = angels.stream().map(a -> angelMapper.convertToDTO(a)).toList();
        return ResponseEntity.ok(CustomResponse.builder()
                .timestamp(LocalDateTime.now())
                .status(HttpStatus.OK)
                .statusCode(HttpStatus.OK.value())
                .data(Map.of("dataRetrieved", angelsDTO))
                .message(angelsDTO.size() + " angels retrieved from page: " + pageNumber)
                .build()
        );
    }

    @GetMapping("/angels/all")
    public ResponseEntity<CustomResponse> getAllAngels() {
        List<Angel> angels = (List<Angel>) angelService.getAngels();
        return ResponseEntity.ok(CustomResponse.builder()
                .timestamp(LocalDateTime.now())
                .status(HttpStatus.OK)
                .statusCode(HttpStatus.OK.value())
                .data(Map.of("dataRetrieved", angels))
                .message("All angels retrieved!")
                .build()
        );
    }

    @GetMapping("/angels/all/count")
    public ResponseEntity<CustomResponse> getAllAngelsCount() {
        List<Angel> angels = (List<Angel>) angelService.getAngels();
        return ResponseEntity.ok(CustomResponse.builder()
                .timestamp(LocalDateTime.now())
                .status(HttpStatus.OK)
                .statusCode(HttpStatus.OK.value())
                .data(Map.of("datumRetrieved", angels))
                .message("Count retrieved!")
                .build()
        );
    }

    @GetMapping("/angels/{religionName}/count")
    public ResponseEntity<CustomResponse> getAllAngelsByReligionNameCount(@PathVariable String religionName) {
        return ResponseEntity.ok(CustomResponse.builder()
                .timestamp(LocalDateTime.now())
                .status(HttpStatus.OK)
                .statusCode(HttpStatus.OK.value())
                .data(Map.of("datumRetrieved", angelService.countAngelsByReligionName(religionName)))
                .message("Count retrieved!")
                .build()
        );
    }

    @GetMapping("/angel/name")
    public ResponseEntity<CustomResponse> getAngelByName(@RequestParam(value = "name") String name) {
        return ResponseEntity.ok(CustomResponse.builder()
                .timestamp(LocalDateTime.now())
                .status(HttpStatus.OK)
                .statusCode(HttpStatus.OK.value())
                .data(Map.of("datumRetrieved", angelService.getAngelByName(name)))
                .message("Angel retrieved!")
                .build()
        );
    }

    @GetMapping("/angel/{id}")
    public ResponseEntity<CustomResponse> getAngelByID(@PathVariable Long id) {
        return ResponseEntity.ok(CustomResponse.builder()
                .timestamp(LocalDateTime.now())
                .status(HttpStatus.OK)
                .statusCode(HttpStatus.OK.value())
                .data(Map.of("datumRetrieved", angelService.getAngelByID(id)))
                .message("Angel retrieved!")
                .build()
        );
    }

    @GetMapping(value = "/angel/image/{fileName}", produces = {MediaType.IMAGE_PNG_VALUE, MediaType.IMAGE_JPEG_VALUE})
    public byte[] getAngelImage(@PathVariable("fileName") String fileName) throws IOException {
        return Files.readAllBytes(Paths.get("src/main/java/com/testament/veltahleon/assets/images/angels/" + fileName));
    }

    @DeleteMapping("/delete/angel/{id}")
    public ResponseEntity<CustomResponse> deleteAngelByID(@PathVariable Long id) {
        return ResponseEntity.ok(CustomResponse.builder()
                .timestamp(LocalDateTime.now())
                .status(HttpStatus.OK)
                .statusCode(HttpStatus.OK.value())
                .data(Map.of("dataDeleted", angelService.deleteAngelByID(id)))
                .message("Angel deleted!")
                .build()
        );
    }

    @PostMapping("/save/angel")
    public ResponseEntity<CustomResponse> saveAngel(@RequestBody @Valid AngelDTO angelDTO) {
        Angel angel = angelMapper.convertToEntity(angelDTO);
        return ResponseEntity.ok(CustomResponse.builder()
                .timestamp(LocalDateTime.now())
                .status(HttpStatus.OK)
                .statusCode(HttpStatus.OK.value())
                .data(Map.of("dataSaved", angelMapper.convertToDTO(angelService.saveAngel(angel))))
                .message("Angel saved!")
                .build()
        );
    }

    @PatchMapping("/update/angel/{id}")
    public ResponseEntity<CustomResponse> updateAngel(@PathVariable("id") Long id, @RequestBody @Valid AngelDTO angelDTO) {
        Angel angel = angelMapper.convertToEntity(angelDTO);
        return ResponseEntity.ok(CustomResponse.builder()
                .timestamp(LocalDateTime.now())
                .status(HttpStatus.OK)
                .statusCode(HttpStatus.OK.value())
                .data(Map.of("dataUpdated", angelMapper.convertToDTO(angelService.updateAngel(id, angel))))
                .message("Angel updated!")
                .build()
        );
    }

    @PutMapping("/modify/angel/{id}")
    public ResponseEntity<CustomResponse> modifyAngel(@PathVariable("id") Long id, @RequestBody @Valid AngelDTO angelDTO) {
        Angel angel = angelMapper.convertToEntity(angelDTO);
        return ResponseEntity.ok(CustomResponse.builder()
                .timestamp(LocalDateTime.now())
                .status(HttpStatus.OK)
                .statusCode(HttpStatus.OK.value())
                .data(Map.of("dataUpdated", angelMapper.convertToDTO(angelService.modifyAngel(id, angel))))
                .message("Angel updated!")
                .build()
        );
    }
}
