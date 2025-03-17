package com.userservice.dto;

import lombok.Data;

import java.time.LocalDate;

@Data
public class AttendanceRecord {
    private int rollNo;
    private char status;
    private LocalDate date;
}
