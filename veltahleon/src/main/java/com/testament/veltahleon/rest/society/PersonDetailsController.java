package com.testament.veltahleon.rest.society;

import com.testament.veltahleon.dto.society.PersonDetailsDTO;
import com.testament.veltahleon.mappers.society.PersonDetailsMapper;
import com.testament.veltahleon.model.entities.society.PersonDetails;
import com.testament.veltahleon.responses.CustomResponse;
import com.testament.veltahleon.services.ifc.society.PersonDetailsService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.util.Map;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/society")
@RequiredArgsConstructor
public class PersonDetailsController {

    @Autowired
    private PersonDetailsService personDetailsService;

    @Autowired
    private PersonDetailsMapper personDetailsMapper;

    public final String imagePath = "src/main/resources/assets/images/people/";

    @GetMapping("/personDetails/{id}/{firstName}/{secondName}")
    public ResponseEntity<CustomResponse> getPersonDetailsByPersonName(@PathVariable String firstName, @PathVariable String secondName) {
        PersonDetailsDTO personDetailsDTO = personDetailsMapper.convertToDTO(personDetailsService.getPersonByFirstNameAndSecondName(firstName, secondName));
        return ResponseEntity.ok(CustomResponse.builder()
                .timestamp(LocalDateTime.now())
                .status(HttpStatus.OK)
                .statusCode(HttpStatus.OK.value())
                .data(Map.of("datumRetrieved", personDetailsDTO))
                .message("Person details retrieved!")
                .build()
        );
    }

    @GetMapping(path = "/personDetails/images/{imageName}", produces = MediaType.IMAGE_PNG_VALUE)
    public byte[] getPersonalImage(@PathVariable("imageName") String imageName) throws IOException {
        //Path image = Paths.get("src/main/resources/assets/images/people/" + imageName);
        return Files.readAllBytes(Path.of(imagePath + imageName));
    }

    @DeleteMapping("/delete/personDetails/{id}")
    public ResponseEntity<CustomResponse> deletePersonDetailsByID(@PathVariable Long id) {
        return ResponseEntity.ok(CustomResponse.builder()
                .timestamp(LocalDateTime.now())
                .status(HttpStatus.OK)
                .statusCode(HttpStatus.OK.value())
                .data(Map.of("dataDeleted", personDetailsService.deletePersonDetailsByID(id)))
                .message("Person detail deleted!")
                .build()
        );
    }

    @PatchMapping("/update/personDetails/{id}")
    public ResponseEntity<CustomResponse> updatePersonDetails(@PathVariable Long id, @RequestBody PersonDetailsDTO personDetailsDTO) {
        PersonDetails personDetails = personDetailsMapper.convertToEntity(personDetailsDTO);
        //PersonDetailsDTO updatedPersonDetailsDTO = personDetailsMapper.convertToDTO(personDetailsService.modifyPersonDetails(id, personDetails));
        return ResponseEntity.ok(CustomResponse.builder()
                .timestamp(LocalDateTime.now())
                .status(HttpStatus.OK)
                .statusCode(HttpStatus.OK.value())
                .data(Map.of("dataUpdated", personDetailsService.updatePersonDetails(id, personDetails)))
                .message("Person details updated!")
                .build()
        );
    }

    @PutMapping("/modify/personDetails/{id}")
    public ResponseEntity<CustomResponse> modifyPersonDetails(@PathVariable Long id, @RequestBody PersonDetailsDTO personDetailsDTO) {
        PersonDetails personDetails = personDetailsMapper.convertToEntity(personDetailsDTO);
        //PersonDetailsDTO updatedPersonDetailsDTO = personDetailsMapper.convertToDTO(personDetailsService.modifyPersonDetails(id, personDetails));
        return ResponseEntity.ok(CustomResponse.builder()
                .timestamp(LocalDateTime.now())
                .status(HttpStatus.OK)
                .statusCode(HttpStatus.OK.value())
                .data(Map.of("dataUpdated", personDetailsService.modifyPersonDetails(id, personDetails)))
                .message("Person details updated!")
                .build()
        );
    }
}
