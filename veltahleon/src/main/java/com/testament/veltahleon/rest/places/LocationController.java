package com.testament.veltahleon.rest.places;

import com.testament.veltahleon.model.entities.places.Location;
import com.testament.veltahleon.responses.CustomResponse;
import com.testament.veltahleon.services.ifc.places.LocationService;
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
public class LocationController {

    @Autowired
    private LocationService locationService;

    @GetMapping("/locations")
    public ResponseEntity<CustomResponse> getPaginatedLocations(@RequestParam(value = "pageNumber", defaultValue = "0") int pageNumber, @RequestParam(value = "pageSize", defaultValue = "10") int pageSize) {
        List<Location> locations = (List<Location>) locationService.getLocationsWithPagination(pageNumber, pageSize);
        return ResponseEntity.ok(CustomResponse.builder()
                .timestamp(LocalDateTime.now())
                .status(HttpStatus.OK)
                .statusCode(HttpStatus.OK.value())
                .data(Map.of("dataRetrieved", locations))
                .message(locations.size() + " locations retrieved from page: " + (pageNumber + 1))
                .build()
        );
    }

    @GetMapping("/locations/all")
    public ResponseEntity<CustomResponse> getAllLocations() {
        List<Location> locations = (List<Location>) locationService.getLocations();
        return ResponseEntity.ok(CustomResponse.builder()
                .timestamp(LocalDateTime.now())
                .status(HttpStatus.OK)
                .statusCode(HttpStatus.OK.value())
                .data(Map.of("dataRetrieved", locations))
                .message("All locations retrieved!")
                .build()
        );
    }

    @GetMapping("/location/{id}")
    public ResponseEntity<CustomResponse> getLocationByID(@PathVariable Long id) {
        return ResponseEntity.ok(CustomResponse.builder()
                .timestamp(LocalDateTime.now())
                .status(HttpStatus.OK)
                .statusCode(HttpStatus.OK.value())
                .data(Map.of("datumRetrieved", locationService.getLocationByID(id)))
                .message("Location retrieved!")
                .build()
        );
    }

    @GetMapping("/location/name")
    public ResponseEntity<CustomResponse> getLocationByLocationName(@RequestParam(value = "locationName") String locationName) {
        return ResponseEntity.ok(CustomResponse.builder()
                .timestamp(LocalDateTime.now())
                .status(HttpStatus.OK)
                .statusCode(HttpStatus.OK.value())
                .data(Map.of("datumRetrieved", locationService.getLocationByName(locationName)))
                .message("Location retrieved!")
                .build()
        );
    }

    @PostMapping("/save/location")
    public ResponseEntity<CustomResponse> saveLocation(@RequestBody @Valid Location location) {
        return ResponseEntity.ok(CustomResponse.builder()
                .timestamp(LocalDateTime.now())
                .status(HttpStatus.OK)
                .statusCode(HttpStatus.OK.value())
                .data(Map.of("dataSaved", locationService.saveLocation(location)))
                .message("Location saved!")
                .build()
        );
    }

    @DeleteMapping("/delete/location/{id}")
    public ResponseEntity<CustomResponse> deleteLocationByID(@PathVariable Long id) {
        return ResponseEntity.ok(CustomResponse.builder()
                .timestamp(LocalDateTime.now())
                .status(HttpStatus.OK)
                .statusCode(HttpStatus.OK.value())
                .data(Map.of("dataDeleted", locationService.deleteLocationByID(id)))
                .message("Location deleted!")
                .build()
        );
    }

    @PatchMapping("/update/location/{id}")
    public ResponseEntity<CustomResponse> updateLocation(@PathVariable Long id, @RequestBody Location location) {
        return ResponseEntity.ok(CustomResponse.builder()
                .timestamp(LocalDateTime.now())
                .status(HttpStatus.OK)
                .statusCode(HttpStatus.OK.value())
                .data(Map.of("dataUpdated", locationService.updateLocation(id, location)))
                .message("Location updated!")
                .build()
        );
    }
}
