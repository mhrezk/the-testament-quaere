package com.testament.veltahleon.rest.society;

import com.testament.veltahleon.model.entities.society.Community;
import com.testament.veltahleon.responses.CustomResponse;
import com.testament.veltahleon.services.ifc.society.CommunityService;
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
public class CommunityController {

    @Autowired
    private CommunityService communityService;

    @GetMapping("/communities")
    public ResponseEntity<CustomResponse> getPaginatedCommunities(@RequestParam(value = "pageNumber", defaultValue = "0") int pageNumber, @RequestParam(value = "pageSize", defaultValue = "5") int pageSize) {
        List<Community> communities;
        communities = (List<Community>) communityService.getCommunitiesWithPagination((pageNumber - 1), pageSize);
        return ResponseEntity.ok(CustomResponse.builder()
                .timestamp(LocalDateTime.now())
                .status(HttpStatus.OK)
                .statusCode(HttpStatus.OK.value())
                .data(Map.of("dataRetrieved", communities))
                .message(communities.size() + " communities retrieved!")
                .build()
        );
    }

    @GetMapping("/communities/all")
    public ResponseEntity<CustomResponse> getAllCommunities() {
        List<Community> communities = (List<Community>) communityService.getCommunities();
        return ResponseEntity.ok(CustomResponse.builder()
                .timestamp(LocalDateTime.now())
                .status(HttpStatus.OK)
                .statusCode(HttpStatus.OK.value())
                .data(Map.of("dataRetrieved", communities))
                .message("All communities retrieved!")
                .build()
        );
    }

    @GetMapping("/communities/all/count")
    public ResponseEntity<CustomResponse> getAllCommunitiesCount() {
        return ResponseEntity.ok(CustomResponse.builder()
                .timestamp(LocalDateTime.now())
                .status(HttpStatus.OK)
                .statusCode(HttpStatus.OK.value())
                .data(Map.of("datumRetrieved", communityService.countCommunities()))
                .message("Count retrieved!")
                .build()
        );
    }

    @GetMapping("/community/{id}")
    public ResponseEntity<CustomResponse> getCommunityByID(@PathVariable Long id) {
        Community community = communityService.getCommunityByID(id);
        return ResponseEntity.ok(CustomResponse.builder()
                .timestamp(LocalDateTime.now())
                .status(HttpStatus.OK)
                .statusCode(HttpStatus.OK.value())
                .data(Map.of("datumRetrieved", community))
                .message("Community retrieved!")
                .build()
        );
    }

    @GetMapping("/community/name")
    public ResponseEntity<CustomResponse> getCommunityByCommunityName(@RequestParam(value = "communityName") String communityName) {
        return ResponseEntity.ok(CustomResponse.builder()
                .timestamp(LocalDateTime.now())
                .status(HttpStatus.OK)
                .statusCode(HttpStatus.OK.value())
                .data(Map.of("datumRetrieved", communityService.getCommunityByName(communityName)))
                .message("Community retrieved!")
                .build()
        );
    }

    @PostMapping("/save/community")
    public ResponseEntity<CustomResponse> saveCommunity(@RequestBody Community community) {
        return ResponseEntity.ok(CustomResponse.builder()
                .timestamp(LocalDateTime.now())
                .status(HttpStatus.OK)
                .statusCode(HttpStatus.OK.value())
                .data(Map.of("dataSaved", communityService.saveCommunity(community)))
                .message("Community saved!")
                .build()
        );
    }

    @DeleteMapping("/delete/community/{id}")
    public ResponseEntity<CustomResponse> deleteCommunityByID(@PathVariable Long id) {
        return ResponseEntity.ok(CustomResponse.builder()
                .timestamp(LocalDateTime.now())
                .status(HttpStatus.OK)
                .statusCode(HttpStatus.OK.value())
                .data(Map.of("dataDeleted", communityService.deleteCommunityByID(id)))
                .message("Community deleted!")
                .build()
        );
    }

    @PatchMapping("/update/community/{id}")
    public ResponseEntity<CustomResponse> updateCommunity(@PathVariable Long id, @RequestBody Community community) {
        return ResponseEntity.ok(CustomResponse.builder()
                .timestamp(LocalDateTime.now())
                .status(HttpStatus.OK)
                .statusCode(HttpStatus.OK.value())
                .data(Map.of("dataUpdated", communityService.updateCommunity(id, community)))
                .message("Community updated!")
                .build()
        );
    }

    @PutMapping("/modify/community/{id}")
    public ResponseEntity<CustomResponse> modifyCommunity(@PathVariable Long id, @RequestBody Community community) {
        return ResponseEntity.ok(CustomResponse.builder()
                .timestamp(LocalDateTime.now())
                .status(HttpStatus.OK)
                .statusCode(HttpStatus.OK.value())
                .data(Map.of("dataUpdated", communityService.modifyCommunity(id, community)))
                .message("Community updated!")
                .build()
        );
    }
}
