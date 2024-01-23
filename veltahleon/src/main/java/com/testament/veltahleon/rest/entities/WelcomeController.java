package com.testament.veltahleon.rest.entities;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping
public class WelcomeController {

    @GetMapping
    public String welcomePage() {
        return "<h1>Welcome to the API of the world of The testament</h1>";
    }
}
