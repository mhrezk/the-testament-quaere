package com.testament.veltahleon.rest.politics;

import com.testament.veltahleon.model.entities.politics.Organization;
import com.testament.veltahleon.responses.CustomResponse;
import com.testament.veltahleon.services.ifc.politics.OrganizationService;
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
@RequestMapping("/politics")
@RequiredArgsConstructor
public class OrganizationController {

    @Autowired
    private OrganizationService organizationService;

    @GetMapping("/organizations")
    public ResponseEntity<CustomResponse> getPaginatedOrganizations(@RequestParam(value = "pageNumber", defaultValue = "0") int pageNumber, @RequestParam(value = "pageSize", defaultValue = "10") int pageSize) {
        List<Organization> organizations = (List<Organization>) organizationService.getOrganizationsWithPagination(pageNumber, pageSize);
        return ResponseEntity.ok(CustomResponse.builder()
                .timestamp(LocalDateTime.now())
                .status(HttpStatus.OK)
                .statusCode(HttpStatus.OK.value())
                .data(Map.of("dataRetrieved", organizations))
                .message(organizations.size() + " organizations retrieved from page: " + (pageNumber + 1))
                .build()
        );
    }

    @GetMapping("/organizations/all")
    public ResponseEntity<CustomResponse> getAllOrganizations() {
        List<Organization> organizations = (List<Organization>) organizationService.getOrganizations();
        return ResponseEntity.ok(CustomResponse.builder()
                .timestamp(LocalDateTime.now())
                .status(HttpStatus.OK)
                .statusCode(HttpStatus.OK.value())
                .data(Map.of("dataRetrieved", organizations))
                .message("All organizations retrieved!")
                .build()
        );
    }

    @GetMapping("/organization/{id}")
    public ResponseEntity<CustomResponse> getOrganizationByID(@PathVariable Long id) {
        return ResponseEntity.ok(CustomResponse.builder()
                .timestamp(LocalDateTime.now())
                .status(HttpStatus.OK)
                .statusCode(HttpStatus.OK.value())
                .data(Map.of("datumRetrieved", organizationService.getOrganizationByID(id)))
                .message("Organization retrieved!")
                .build()
        );
    }

    @GetMapping("/organization/name")
    public ResponseEntity<CustomResponse> getOrganizationByOrganizationName(@RequestParam(value = "organizationName") String organizationName) {
        return ResponseEntity.ok(CustomResponse.builder()
                .timestamp(LocalDateTime.now())
                .status(HttpStatus.OK)
                .statusCode(HttpStatus.OK.value())
                .data(Map.of("datumRetrieved", organizationService.getOrganizationByName(organizationName)))
                .message("Organization retrieved!")
                .build()
        );
    }

    @GetMapping(value = "/organization/image/{fileName}", produces = {MediaType.IMAGE_PNG_VALUE, MediaType.IMAGE_JPEG_VALUE, MediaType.IMAGE_GIF_VALUE})
    public byte[] getOrganizationSymbol(@PathVariable("fileName") String fileName) throws IOException {
        return Files.readAllBytes(Paths.get("src/main/java/com/testament/veltahleon/assets/images/symbols/" + fileName));
    }

    @PostMapping("/save/organization")
    public ResponseEntity<CustomResponse> saveOrganization(@RequestBody @Valid Organization organization) {
        return ResponseEntity.ok(CustomResponse.builder()
                .timestamp(LocalDateTime.now())
                .status(HttpStatus.OK)
                .statusCode(HttpStatus.OK.value())
                .data(Map.of("dataSaved", organizationService.saveOrganization(organization)))
                .message("Organization saved!")
                .build()
        );
    }

    @DeleteMapping("/delete/organization/{id}")
    public ResponseEntity<CustomResponse> deleteOrganizationByID(@PathVariable Long id) {
        return ResponseEntity.ok(CustomResponse.builder()
                .timestamp(LocalDateTime.now())
                .status(HttpStatus.OK)
                .statusCode(HttpStatus.OK.value())
                .data(Map.of("dataDeleted", organizationService.deleteOrganizationByID(id)))
                .message("Organization deleted!")
                .build()
        );
    }

    @PatchMapping("/update/organization/{id}")
    public ResponseEntity<CustomResponse> updateOrganization(@PathVariable Long id, @RequestBody Organization organization) {
        return ResponseEntity.ok(CustomResponse.builder()
                .timestamp(LocalDateTime.now())
                .status(HttpStatus.OK)
                .statusCode(HttpStatus.OK.value())
                .data(Map.of("dataUpdated", organizationService.updateOrganization(id, organization)))
                .message("Organization updated!")
                .build()
        );
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<CustomResponse> modifyOrganization(@PathVariable Long id, @RequestBody Organization organization) {
        return ResponseEntity.ok(CustomResponse.builder()
                .timestamp(LocalDateTime.now())
                .status(HttpStatus.OK)
                .statusCode(HttpStatus.OK.value())
                .data(Map.of("dataUpdated", organizationService.modifyOrganization(id, organization)))
                .message("Organization updated!")
                .build()
        );
    }
}
