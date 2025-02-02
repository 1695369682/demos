package com.example.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class Employee {
    private Long id;

    private String username;

    private String name;

    private String password;

    private String email;

    private Integer age;

    private boolean admin;

    private String salt;

//    private Long deptId;
    private Department dept;
}