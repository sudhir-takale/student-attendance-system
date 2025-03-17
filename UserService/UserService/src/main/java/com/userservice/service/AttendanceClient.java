package com.userservice.service;

import com.userservice.dto.AttendanceRecord;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@FeignClient(url = "http://localhost:8082", name = "attendanceclient")
public interface AttendanceClient {

    @GetMapping("/api/attendance/student/{rollNo}")
    List<AttendanceRecord> getAttendanceRecordOf(@PathVariable int rollNo);
}
