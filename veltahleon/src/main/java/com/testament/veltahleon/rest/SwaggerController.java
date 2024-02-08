package com.testament.veltahleon.rest;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping
public class SwaggerController {

    @GetMapping("/v3/api-docs")
    public String welcomePage() {
        return "<h1>Welcome to the API of the world of The testament</h1>";
    }
}
