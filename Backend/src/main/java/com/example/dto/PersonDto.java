package com.example.dto;

import lombok.Data;

import java.io.Serializable;

@Data
public class PersonDto implements Serializable {
    private long id;
    private String name;
    private String gender;
    private String address;
    private int age;
}
