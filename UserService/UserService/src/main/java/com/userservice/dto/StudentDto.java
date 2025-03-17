package com.userservice.dto;

import lombok.Data;

import java.util.List;

@Data
public class StudentDto {

    private int rollNo;
    private String name;
    private String lastName;
    private String standard;
    transient List<AttendanceRecord> records;

    public StudentDto(int rollNo, String name, String lastName, String standard) {
        this.rollNo = rollNo;
        this.name = name;
        this.lastName = lastName;
        this.standard = standard;
    }
}
