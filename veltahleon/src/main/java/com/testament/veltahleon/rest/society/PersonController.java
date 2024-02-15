package com.testament.veltahleon.rest.society;

import com.testament.veltahleon.model.entities.society.Person;
import com.testament.veltahleon.responses.CustomResponse;
import com.testament.veltahleon.services.ifc.society.PersonService;
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

@RestController
@RequestMapping("/society")
@RequiredArgsConstructor
public class PersonController {

    @Autowired
    private PersonService personService;

    @GetMapping("/persons")
    public ResponseEntity<CustomResponse> getPaginatedPersons(@RequestParam(value = "pageNumber", defaultValue = "0") int pageNumber, @RequestParam(value = "pageSize", defaultValue = "10") int pageSize) {
        List<Person> persons = (List<Person>) personService.getPersonsWithPagination(pageNumber, pageSize);
        return ResponseEntity.ok(CustomResponse.builder()
                .timestamp(LocalDateTime.now())
                .status(HttpStatus.OK)
                .statusCode(HttpStatus.OK.value())
                .data(Map.of("paginatedPersons", persons))
                .message(persons.size() + " persons retrieved from page: " + (pageNumber + 1))
                .build()
        );
    }

    @GetMapping("/persons/all")
    public ResponseEntity<CustomResponse> getAllPersons() {
        List<Person> persons = (List<Person>) personService.getPersons();
        return ResponseEntity.ok(CustomResponse.builder()
                .timestamp(LocalDateTime.now())
                .status(HttpStatus.OK)
                .statusCode(HttpStatus.OK.value())
                .data(Map.of("allPersons", persons))
                .message("All persons retrieved!")
                .build()
        );
    }

    @GetMapping("/person/{id}")
    public ResponseEntity<CustomResponse> getPersonByID(@PathVariable Long id) {
        return ResponseEntity.ok(CustomResponse.builder()
                .timestamp(LocalDateTime.now())
                .status(HttpStatus.OK)
                .statusCode(HttpStatus.OK.value())
                .data(Map.of("queriedPersonByID", personService.getPersonByID(id)))
                .message("Person retrieved!")
                .build()
        );
    }

    @GetMapping("/person/name")
    public ResponseEntity<CustomResponse> getPersonByPersonName(@RequestParam(value = "personName") String personName) {
        return ResponseEntity.ok(CustomResponse.builder()
                .timestamp(LocalDateTime.now())
                .status(HttpStatus.OK)
                .statusCode(HttpStatus.OK.value())
                .data(Map.of("queriedPersonByName", personService.getPersonByName(personName)))
                .message("Person retrieved!")
                .build()
        );
    }

    @GetMapping(value = "/people/image/{fileName}", produces = {MediaType.IMAGE_PNG_VALUE, MediaType.IMAGE_JPEG_VALUE, MediaType.IMAGE_GIF_VALUE})
    public byte[] getPersonImage(@PathVariable("fileName") String fileName) throws IOException {
        return Files.readAllBytes(Paths.get("src/main/java/com/testament/veltahleon/assets/images/people/" + fileName));
    }

    @PostMapping("/save/person")
    public ResponseEntity<CustomResponse> savePerson(@RequestBody @Valid Person person) {
        return ResponseEntity.ok(CustomResponse.builder()
                .timestamp(LocalDateTime.now())
                .status(HttpStatus.OK)
                .statusCode(HttpStatus.OK.value())
                .data(Map.of("savedPerson", personService.savePerson(person)))
                .message("Person saved!")
                .build()
        );
    }

    @DeleteMapping("/delete/person/{id}")
    public ResponseEntity<CustomResponse> deletePersonByID(@PathVariable Long id) {
        return ResponseEntity.ok(CustomResponse.builder()
                .timestamp(LocalDateTime.now())
                .status(HttpStatus.OK)
                .statusCode(HttpStatus.OK.value())
                .data(Map.of("isPersonDeleted", personService.deletePersonByID(id)))
                .message("Person deleted!")
                .build()
        );
    }

    @PatchMapping("/update/person/{id}")
    public ResponseEntity<CustomResponse> updatePerson(@PathVariable Long id, @RequestBody @Valid Person person) {
        return ResponseEntity.ok(CustomResponse.builder()
                .timestamp(LocalDateTime.now())
                .status(HttpStatus.OK)
                .statusCode(HttpStatus.OK.value())
                .data(Map.of("updatedPerson", personService.updatePerson(id, person)))
                .message("Person updated!")
                .build()
        );
    }
}
