package com.testament.veltahleon.rest.places;

import com.testament.veltahleon.dto.places.NationDetailsDTO;
import com.testament.veltahleon.mappers.places.NationDetailsMapper;
import com.testament.veltahleon.model.entities.places.NationDetails;
import com.testament.veltahleon.responses.CustomResponse;
import com.testament.veltahleon.services.ifc.places.NationDetailsService;
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
import java.util.Map;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/places")
@RequiredArgsConstructor
public class NationDetailsController {

    @Autowired
    private NationDetailsService nationDetailsService;

    @Autowired
    private NationDetailsMapper nationDetailsMapper;

    public final String IMAGE_PATH = "src/main/resources/assets/images/flags/";

    @GetMapping("/nationDetails/{nationName}")
    public ResponseEntity<CustomResponse> getNationDetailsByNationName(@PathVariable String nationName) {
        NationDetailsDTO nationDetailsDTO = nationDetailsMapper.convertToDTO(nationDetailsService.getNationDetailsByNationName(nationName));
        return ResponseEntity.ok(CustomResponse.builder()
                .timestamp(LocalDateTime.now())
                .status(HttpStatus.OK)
                .statusCode(HttpStatus.OK.value())
                .data(Map.of("datumRetrieved", nationDetailsDTO))
                .message("Nation details retrieved!")
                .build()
        );
    }

    @GetMapping(path = "/nationDetails/images/{imageName}", produces = MediaType.IMAGE_PNG_VALUE)
    public byte[] getNationalImage(@PathVariable("imageName") String imageName) throws IOException {
        return Files.readAllBytes(Path.of(IMAGE_PATH + imageName));
    }

    @DeleteMapping("/delete/nationDetails/{id}")
    public ResponseEntity<CustomResponse> deleteNationDetailsByID(@PathVariable Long id) {
        return ResponseEntity.ok(CustomResponse.builder()
                .timestamp(LocalDateTime.now())
                .status(HttpStatus.OK)
                .statusCode(HttpStatus.OK.value())
                .data(Map.of("dataDeleted", nationDetailsService.deleteNationDetailsByID(id)))
                .message("Nation details deleted!")
                .build()
        );
    }

    @PatchMapping("/update/nationDetails/{id}")
    public ResponseEntity<CustomResponse> updateNationDetails(@PathVariable Long id, @RequestBody NationDetailsDTO nationDetailsDTO) {
        NationDetails nationDetails = nationDetailsMapper.convertToEntity(nationDetailsDTO);
        return ResponseEntity.ok(CustomResponse.builder()
                .timestamp(LocalDateTime.now())
                .status(HttpStatus.OK)
                .statusCode(HttpStatus.OK.value())
                .data(Map.of("dataUpdated", nationDetailsService.updateNationDetails(id, nationDetails)))
                .message("Nation details updated!")
                .build()
        );
    }

    @PutMapping("/modify/nationDetails/{id}")
    public ResponseEntity<CustomResponse> modifyNationDetails(@PathVariable Long id, @RequestBody NationDetailsDTO nationDetailsDTO) {
        NationDetails nationDetails = nationDetailsMapper.convertToEntity(nationDetailsDTO);
        return ResponseEntity.ok(CustomResponse.builder()
                .timestamp(LocalDateTime.now())
                .status(HttpStatus.OK)
                .statusCode(HttpStatus.OK.value())
                .data(Map.of("dataUpdated", nationDetailsMapper.convertToDTO(nationDetailsService.modifyNationDetails(id, nationDetails))))
                .message("Nation details updated!")
                .build()
        );
    }
}
