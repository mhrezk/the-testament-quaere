//package com.testament.veltahleon.rest.images;
//
//import lombok.RequiredArgsConstructor;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.core.io.Resource;
//import org.springframework.core.io.ResourceLoader;
//import org.springframework.http.HttpHeaders;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.*;
//
//@CrossOrigin(origins = "http://localhost:4200")
//@RestController
////@RequestMapping("/images")
//@RequiredArgsConstructor
//public class ImageController {
//
//    @Autowired
//    private final ResourceLoader resourceLoader;
//
//    @GetMapping("/images/people/{imageName:.+}")
//    public ResponseEntity<Resource> getImage(@PathVariable String imageName) {
//        try {
//            Resource resource = resourceLoader.getResource("classpath:assets/images/people/" + imageName);
//            return ResponseEntity.ok()
//                    .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + resource.getFilename() + "\"")
//                    .body(resource);
//        } catch (Exception e) {
//            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
//        }
//    }
//}
