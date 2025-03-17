package com.attendanceservice.service;


import com.attendanceservice.dto.Student;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@FeignClient(url = "http://localhost:8081", name = "studentclient")
public interface StudentClient {
    @GetMapping("/api/students")
    List<Student> getStudents();
}
