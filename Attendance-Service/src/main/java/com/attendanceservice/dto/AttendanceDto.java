package com.attendanceservice.dto;

import lombok.Data;

import java.time.LocalDate;

@Data
public class AttendanceDto {
    private final int rollNo;
    private final LocalDate date;
    private final char status;
    private Student student;

    public AttendanceDto(int rollNo, LocalDate date, char status) {
        this.rollNo = rollNo;
        this.date = LocalDate.now();
        this.status = status;
    }
}
