package com.sayed.controller;


import com.sayed.dto.Employee;
import com.sayed.dto.EmployeeDao;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RequestMapping("/management")
@RestController
@RequiredArgsConstructor
public class ManagementController {

    private final EmployeeDao employeeDao;

    @PreAuthorize("hasAuthority('course:read')")
    @GetMapping
    public ResponseEntity<String> sayHelloFromManagement() {
        log.info("Say Hello From Management");
        return ResponseEntity.ok("Hello from Management Controller");
    }

    @PreAuthorize("hasAuthority('course:read')")
    @GetMapping("/employee/{id}")
    public ResponseEntity<Employee> getEmployeeById(@PathVariable Long id) {
        log.info("getEmployeeById : {}", id);
        Employee employee = employeeDao.getEmployees()
                .stream().filter(emp->emp.getId().equals(id)).findFirst().
                orElseThrow(() -> new IllegalStateException(
                "Employee " + id + " does not exists"
        ));
        return ResponseEntity.ok(employee);
    }

    @PreAuthorize("hasAuthority('course:write')")
    @PostMapping("/employee/{id}")
    public ResponseEntity<Void> addEmployeeById(@PathVariable Long id,
                                                    @RequestBody Employee employee) {
        log.info("addEmployeeById : {}", id);
        log.info(employee.setId(id).toString());

        return ResponseEntity.ok().build();
    }

}
