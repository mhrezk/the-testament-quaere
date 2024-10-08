package com.testament.veltahleon.rest.history;

import com.testament.veltahleon.model.entities.history.Timeline;
import com.testament.veltahleon.responses.CustomResponse;
import com.testament.veltahleon.services.ifc.history.TimelineService;
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
@RequestMapping("/history")
@RequiredArgsConstructor
public class TimelineController {

    @Autowired
    private TimelineService timelineService;

    @GetMapping("/timelines")
    public ResponseEntity<CustomResponse> getPaginatedTimelines(@RequestParam(value = "pageNumber", defaultValue = "0") int pageNumber, @RequestParam(value = "pageSize", defaultValue = "10") int pageSize) {
        List<Timeline> timelines = (List<Timeline>) timelineService.getPaginatedTimelines((pageNumber - 1), pageSize);
        return ResponseEntity.ok(CustomResponse.builder()
                .timestamp(LocalDateTime.now())
                .status(HttpStatus.OK)
                .statusCode(HttpStatus.OK.value())
                .data(Map.of("dataRetrieved", timelines))
                .message(timelines.size() + " timelines retrieved from page: " + pageNumber)
                .build()
        );
    }

    @GetMapping("/timelines/all")
    public ResponseEntity<CustomResponse> getTimelines() {
        List<Timeline> timelines = (List<Timeline>) timelineService.getTimelines();
        return ResponseEntity.ok(CustomResponse.builder()
                .timestamp(LocalDateTime.now())
                .status(HttpStatus.OK)
                .statusCode(HttpStatus.OK.value())
                .data(Map.of("dataRetrieved", timelines))
                .message("All timelines retrieved!")
                .build()
        );
    }

    @GetMapping("/timelines/all/count")
    public ResponseEntity<CustomResponse> getTimelineByID() {
        return ResponseEntity.ok(CustomResponse.builder()
                .timestamp(LocalDateTime.now())
                .status(HttpStatus.OK)
                .statusCode(HttpStatus.OK.value())
                .data(Map.of("datumRetrieved", timelineService.countTimelines()))
                .message("All timelines retrieved!")
                .build()
        );
    }

    @GetMapping("/timeline/{id}")
    public ResponseEntity<CustomResponse> getTimelineByID(@PathVariable Long id) {
        return ResponseEntity.ok(CustomResponse.builder()
                .timestamp(LocalDateTime.now())
                .status(HttpStatus.OK)
                .statusCode(HttpStatus.OK.value())
                .data(Map.of("datumRetrieved", timelineService.getTimelineById(id)))
                .message("All timelines retrieved!")
                .build()
        );
    }

    @PostMapping("/save/timeline")
    public ResponseEntity<CustomResponse> saveTimeline(@RequestBody @Valid Timeline timeline) {
        return ResponseEntity.ok(CustomResponse.builder()
                .timestamp(LocalDateTime.now())
                .status(HttpStatus.OK)
                .statusCode(HttpStatus.OK.value())
                .data(Map.of("dataSaved", timelineService.saveTimeline(timeline)))
                .message("All timelines retrieved!")
                .build()
        );
    }

    @DeleteMapping("/delete/timeline/{id}")
    public ResponseEntity<CustomResponse> saveTimeline(@PathVariable Long id) {
        return ResponseEntity.ok(CustomResponse.builder()
                .timestamp(LocalDateTime.now())
                .status(HttpStatus.OK)
                .statusCode(HttpStatus.OK.value())
                .data(Map.of("dataDeleted", timelineService.deleteTimelineByID(id)))
                .message("Timeline deleted!")
                .build()
        );
    }

    @PutMapping("/modify/timeline/{id}")
    public ResponseEntity<CustomResponse> modifyTimeline(@PathVariable Long id, @RequestBody @Valid Timeline timeline) {
        return ResponseEntity.ok(CustomResponse.builder()
                .timestamp(LocalDateTime.now())
                .status(HttpStatus.OK)
                .statusCode(HttpStatus.OK.value())
                .data(Map.of("dataUpdated", timelineService.modifyTimeline(id, timeline)))
                .message("Timeline updated!")
                .build()
        );
    }
}
