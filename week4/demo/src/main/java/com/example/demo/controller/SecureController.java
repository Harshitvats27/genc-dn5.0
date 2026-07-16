package com.example.demo.controller;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
public class SecureController {

    @GetMapping("/secure")
    public Mono<String> secure() {
        return Mono.just("This is a secure endpoint protected by JWT!");
    }
}