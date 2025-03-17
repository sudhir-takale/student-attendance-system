package com.attendanceservice.domain.model;

import com.attendanceservice.dto.Student;
import lombok.Builder;
import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;

@Document
@Data
@Builder
public class AttendanceRecord {
    private String id;
    private int rollNo;
    private LocalDate date;
    private char status;
    transient Student student;
}
