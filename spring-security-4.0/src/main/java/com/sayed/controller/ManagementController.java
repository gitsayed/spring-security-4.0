package com.sayed.controller;


import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RequestMapping("/management")
@RestController
public class ManagementController {

    @GetMapping
    public ResponseEntity<String> sayHelloFromManagement() {
        log.info("Say Hello From Management");
        return ResponseEntity.ok("Hello from Management Controller");
    }
}
