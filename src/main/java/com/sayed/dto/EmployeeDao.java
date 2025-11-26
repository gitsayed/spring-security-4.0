package com.sayed.dto;

import java.util.Arrays;
import java.util.List;

public class EmployeeDao {

    public static final List<Employee> employees = Arrays.asList(
            new Employee().setId(1L).setName("mostofa").setRole("emp"),
            new Employee().setId(2L).setName("bodrul").setRole("emp"),
            new Employee().setId(3L).setName("nasir").setRole("emp"),
            new Employee().setId(4L).setName("nasrin").setRole("emp"),
            new Employee().setId(5L).setName("yahia").setRole("mgt"),
            new Employee().setId(6L).setName("arif").setRole("mgt"),
            new Employee().setId(7L).setName("alamin").setRole("mgt")
    );
}
