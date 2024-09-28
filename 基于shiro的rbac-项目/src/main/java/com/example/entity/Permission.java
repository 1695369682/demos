package com.example.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

@Data
@Component
@AllArgsConstructor
@NoArgsConstructor
public class Permission {
    /** */
    private Long id;

    /** */
    private String name;

    /** */
    private String expression;
    public Permission(String name,String expression)
    {
        this.expression=expression;
        this.name=name;
    }
}