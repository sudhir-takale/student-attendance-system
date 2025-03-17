package com.attendanceservice.controller.controller;

import com.attendanceservice.dto.AttendanceDto;
import com.attendanceservice.service.AttendanceService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/api")
@RestController
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

    @GetMapping("/attendance")
    public List<AttendanceDto> getAttendance() {
        return attendanceService.getAttendance();
    }

}
