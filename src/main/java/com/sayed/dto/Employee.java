package com.sayed.dto;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
@JsonIgnoreProperties(ignoreUnknown = true)
public class Employee {

    Long id;
    String name;
    String address;
    Integer age;
    String email;
    String mobileNo;
    UserRole role;
}
