package com.sayed.dto;

import lombok.Data;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;

@Data
@Component
public class EmployeeDao {

    private List<Employee> employees = Arrays.asList(
            new Employee().setId(1L).setName("mostofa").setRole(UserRole.EMP),
            new Employee().setId(2L).setName("bodrul").setRole(UserRole.EMP),
            new Employee().setId(3L).setName("nasir").setRole(UserRole.EMP),
            new Employee().setId(4L).setName("nasrin").setRole(UserRole.EMP),
            new Employee().setId(5L).setName("yahia").setRole(UserRole.ADMIN),
            new Employee().setId(6L).setName("arif").setRole(UserRole.ADMIN),
            new Employee().setId(7L).setName("alamin").setRole(UserRole.ADMIN)
    );


}
