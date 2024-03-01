package com.testament.veltahleon.rest.places;

import com.testament.veltahleon.model.entities.places.Province;
import com.testament.veltahleon.responses.CustomResponse;
import com.testament.veltahleon.services.ifc.places.ProvinceService;
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
public class ProvinceController {

    @Autowired
    private ProvinceService provinceService;

    @GetMapping("/provinces")
    public ResponseEntity<CustomResponse> getPaginatedProvinces(@RequestParam(value = "pageNumber", defaultValue = "0") int pageNumber, @RequestParam(value = "pageSize", defaultValue = "10") int pageSize) {
        List<Province> provinces = (List<Province>) provinceService.getProvincesWithPagination(pageNumber, pageSize);
        return ResponseEntity.ok(CustomResponse.builder()
                .timestamp(LocalDateTime.now())
                .status(HttpStatus.OK)
                .statusCode(HttpStatus.OK.value())
                .data(Map.of("dataRetrieved", provinces))
                .message(provinces.size() + " Provinces retrieved from page: " + (pageNumber + 1))
                .build()
        );
    }

    @GetMapping("/provinces/all")
    public ResponseEntity<CustomResponse> getAllProvinces() {
        List<Province> provinces = (List<Province>) provinceService.getProvinces();
        return ResponseEntity.ok(CustomResponse.builder()
                .timestamp(LocalDateTime.now())
                .status(HttpStatus.OK)
                .statusCode(HttpStatus.OK.value())
                .data(Map.of("dataRetrieved", provinces))
                .message("All Provinces retrieved!")
                .build()
        );
    }

    @GetMapping("/province/{id}")
    public ResponseEntity<CustomResponse> getProvinceByID(@PathVariable Long id) {
        return ResponseEntity.ok(CustomResponse.builder()
                .timestamp(LocalDateTime.now())
                .status(HttpStatus.OK)
                .statusCode(HttpStatus.OK.value())
                .data(Map.of("dataRetrieved", provinceService.getProvinceByID(id)))
                .message("Province retrieved!")
                .build()
        );
    }

    @GetMapping("/province/name")
    public ResponseEntity<CustomResponse> getProvinceByProvinceName(@RequestParam(value = "provinceName") String provinceName) {
        return ResponseEntity.ok(CustomResponse.builder()
                .timestamp(LocalDateTime.now())
                .status(HttpStatus.OK)
                .statusCode(HttpStatus.OK.value())
                .data(Map.of("dataRetrieved", provinceService.getProvinceByName(provinceName)))
                .message("Province retrieved!")
                .build()
        );
    }

    @PostMapping("/save/province")
    public ResponseEntity<CustomResponse> saveProvince(@RequestBody @Valid Province province) {
        return ResponseEntity.ok(CustomResponse.builder()
                .timestamp(LocalDateTime.now())
                .status(HttpStatus.OK)
                .statusCode(HttpStatus.OK.value())
                .data(Map.of("dataSaved", provinceService.saveProvince(province)))
                .message("Province saved!")
                .build()
        );
    }

    @DeleteMapping("/delete/province/{id}")
    public ResponseEntity<CustomResponse> deleteProvinceByID(@PathVariable Long id) {
        return ResponseEntity.ok(CustomResponse.builder()
                .timestamp(LocalDateTime.now())
                .status(HttpStatus.OK)
                .statusCode(HttpStatus.OK.value())
                .data(Map.of("dataDeleted", provinceService.deleteProvinceByID(id)))
                .message("Province deleted!")
                .build()
        );
    }

    @PatchMapping("/update/Province/{id}")
    public ResponseEntity<CustomResponse> updateProvince(@PathVariable Long id, @RequestBody Province province) {
        return ResponseEntity.ok(CustomResponse.builder()
                .timestamp(LocalDateTime.now())
                .status(HttpStatus.OK)
                .statusCode(HttpStatus.OK.value())
                .data(Map.of("dataUpdated", provinceService.updateProvince(id, province)))
                .message("Province updated!")
                .build()
        );
    }
}
