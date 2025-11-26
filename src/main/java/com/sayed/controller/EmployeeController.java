package com.sayed.controller;


import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@Slf4j
@RequestMapping("/employee")
@RestController
public class EmployeeController {

    @GetMapping
    public ResponseEntity<String> sayHelloFromEmployee() {
        log.info("Say Hello From Employee");
        return ResponseEntity.ok("Hello from Employee Controller");
    }
}
