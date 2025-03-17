package com.attendanceservice.service;

import com.attendanceservice.domain.model.AttendanceRecord;
import com.attendanceservice.dto.AttendanceDto;
import com.attendanceservice.dto.Student;
import com.attendanceservice.repository.AttendanceRepository;
import com.attendanceservice.utility.AttendanceMapper;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class AttendanceService {
    private final AttendanceRepository attendanceRepository;
    private final StudentClient studentClient;

    public AttendanceService(AttendanceRepository attendanceRepository, StudentClient studentClient) {
        this.attendanceRepository = attendanceRepository;
        this.studentClient = studentClient;
    }


    public AttendanceDto create(AttendanceDto attendanceDto) {
        AttendanceRecord attendanceRecord = AttendanceRecord.builder()
                .date(attendanceDto.getDate())
                .rollNo(attendanceDto.getRollNo())
                .status(attendanceDto.getStatus())
                .build();
        AttendanceRecord saved = attendanceRepository.save(attendanceRecord);
        return AttendanceMapper.convertToDto(saved);
    }

    public List<Student> markAttendance() {
        return studentClient.getStudents();
    }

    public List<AttendanceDto> getAttendanceSheet() {
        List<Student> students = studentClient.getStudents();

        Map<Integer, Student> studentMap = students.stream()
                .collect(Collectors.toMap(Student::getRollNo, student -> student));

        return attendanceRepository.findAll()
                .stream()
                .map(AttendanceMapper::convertToDto)
                .peek(attendanceDto -> {
                    Student student = studentMap.get(attendanceDto.getRollNo());
                    if (student != null) {
                        attendanceDto.setStudent(student);
                    }
                })
                .toList();
    }

    public List<AttendanceDto> getRecordOf(int rollNo) {
        List<AttendanceRecord> records = attendanceRepository.findByRollNo(rollNo);
        List<AttendanceDto> dtos = new ArrayList<>();
        for (AttendanceRecord record : records) {
            AttendanceDto attendanceDto = new AttendanceDto(record.getRollNo(), record.getDate(), record.getStatus());
            dtos.add(attendanceDto);
        }
        return dtos;
    }
}
