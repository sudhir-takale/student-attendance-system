package com.attendanceservice.repository;

import com.attendanceservice.domain.model.AttendanceRecord;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface AttendanceRepository extends MongoRepository<AttendanceRecord, String> {
    List<AttendanceRecord> findByRollNo(int rollNo);
}
