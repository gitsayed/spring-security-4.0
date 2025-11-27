package com.sayed.controller;


import com.sayed.dto.Employee;
import com.sayed.dto.EmployeeDao;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@Slf4j
@RequestMapping("/employee")
@RestController
@RequiredArgsConstructor
public class EmployeeController {

    private final EmployeeDao employeeDao;

    @GetMapping
    public ResponseEntity<String> sayHelloFromEmployee() {
        log.info("Say Hello From Employee");
        return ResponseEntity.ok("Hello from Employee Controller");
    }


    @PreAuthorize("hasAuthority('emp:read')")
    @GetMapping("/{id}")
    public ResponseEntity<Employee> getEmployeeById(@PathVariable Long id) {
        log.info("getEmployeeById : {}", id);
        Employee employee = employeeDao.getEmployees()
                .stream().filter(emp->emp.getId().equals(id)).findFirst().
                orElseThrow(() -> new IllegalStateException(
                        "Employee " + id + " does not exists"
                ));
        return ResponseEntity.ok(employee);
    }

    @PreAuthorize("hasAuthority('emp:write')")
    @PostMapping("/{id}")
    public ResponseEntity<Void> addEmployeeById(@PathVariable Long id,
                                                    @RequestBody Employee employee) {
        log.info("addEmployeeById : {}", id);
        log.info(employee.setId(id).toString());
        return ResponseEntity.ok().build();
    }

}
