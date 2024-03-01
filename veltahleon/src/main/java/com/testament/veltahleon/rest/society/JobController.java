package com.testament.veltahleon.rest.society;

import com.testament.veltahleon.model.entities.society.Job;
import com.testament.veltahleon.responses.CustomResponse;
import com.testament.veltahleon.services.ifc.society.JobService;
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
@RequestMapping("/society")
@RequiredArgsConstructor
public class JobController {

    @Autowired
    private JobService jobService;

    @GetMapping("/jobs/all")
    public ResponseEntity<CustomResponse> getAllJobs() {
        List<Job> jobs = (List<Job>) jobService.getJobs();
        return ResponseEntity.ok(CustomResponse.builder()
                .timestamp(LocalDateTime.now())
                .status(HttpStatus.OK)
                .statusCode(HttpStatus.OK.value())
                .data(Map.of("allJobs", jobs))
                .message("All jobs retrieved!")
                .build()
        );
    }

    @GetMapping("/job/{id}")
    public ResponseEntity<CustomResponse> getJobByID(@PathVariable Long id) {
        return ResponseEntity.ok(CustomResponse.builder()
                .timestamp(LocalDateTime.now())
                .status(HttpStatus.OK)
                .statusCode(HttpStatus.OK.value())
                .data(Map.of("queriedJobByID", jobService.getJobByID(id)))
                .message("Job retrieved!")
                .build()
        );
    }

    @PostMapping("/save/job")
    public ResponseEntity<CustomResponse> saveJob(@RequestBody @Valid Job job) {
        return ResponseEntity.ok(CustomResponse.builder()
                .timestamp(LocalDateTime.now())
                .status(HttpStatus.OK)
                .statusCode(HttpStatus.OK.value())
                .data(Map.of("savedJob", jobService.saveJob(job)))
                .message("Job saved!")
                .build()
        );
    }

    @DeleteMapping("/delete/job/{id}")
    public ResponseEntity<CustomResponse> deleteJobByID(@PathVariable Long id) {
        return ResponseEntity.ok(CustomResponse.builder()
                .timestamp(LocalDateTime.now())
                .status(HttpStatus.OK)
                .statusCode(HttpStatus.OK.value())
                .data(Map.of("isJobDeleted", jobService.deleteJobByID(id)))
                .message("Job deleted!")
                .build()
        );
    }

    @PatchMapping("/update/job/{id}")
    public ResponseEntity<CustomResponse> updateJob(@PathVariable Long id, @RequestBody @Valid Job job) {
        return ResponseEntity.ok(CustomResponse.builder()
                .timestamp(LocalDateTime.now())
                .status(HttpStatus.OK)
                .statusCode(HttpStatus.OK.value())
                .data(Map.of("updatedJob", jobService.updateJob(id, job)))
                .message("Job updated!")
                .build()
        );
    }
}
