package com.attendanceservice.controller;

import com.attendanceservice.dto.AttendanceDto;
import com.attendanceservice.dto.Student;
import com.attendanceservice.service.AttendanceService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api")
public class AttendanceController {

    private final AttendanceService attendanceService;

    public AttendanceController(AttendanceService attendanceService) {
        this.attendanceService = attendanceService;
    }


    @PostMapping("/attendance")
    public ResponseEntity<AttendanceDto> create(@RequestBody AttendanceDto attendanceDto) {
        AttendanceDto response = attendanceService.create(attendanceDto);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/mark-attendance")
    public List<Student> markAttendance() {
        return attendanceService.markAttendance();
    }

    @GetMapping("/attendance")
    public List<AttendanceDto> getAttendanceSheet() {
        return attendanceService.getAttendanceSheet();
    }

    @GetMapping("/attendance/student/{rollNo}")
    List<AttendanceDto> getAttendanceRecordOf(@PathVariable int rollNo) {
        return attendanceService.getRecordOf(rollNo);
    }

}
