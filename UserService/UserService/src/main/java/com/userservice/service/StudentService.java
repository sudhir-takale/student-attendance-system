package com.userservice.service;

import com.userservice.domain.model.entity.Student;
import com.userservice.dto.AttendanceRecord;
import com.userservice.dto.StudentDto;
import com.userservice.repository.StudentRepository;
import com.userservice.service.exception.InvalidRollNumberException;
import com.userservice.util.StudentMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class StudentService {
    private final AttendanceClient attendanceClient;
    private final StudentRepository studentRepository;

    public StudentService(AttendanceClient attendanceClient, StudentRepository studentRepository) {
        this.attendanceClient = attendanceClient;
        this.studentRepository = studentRepository;
    }

    public StudentDto create(StudentDto studentDto) {
        Student student = Student.builder().rollNo(studentDto.getRollNo()).name(studentDto.getName())
                .standard(studentDto.getStandard())
                .lastName(studentDto.getLastName())
                .build();
        Student saved = studentRepository.save(student);

        return StudentMapper.convertToDto(saved);
    }

    public StudentDto getStudentBy(int rollNo) {
//        if(rollNo < 1) throw new exception

        Optional<Student> existingStudent = studentRepository.findByRollNo(rollNo);
        List<AttendanceRecord> attendanceRecords = attendanceClient.getAttendanceRecordOf(rollNo);
        StudentDto studentDto = StudentMapper.convertToDto(existingStudent.get());
        studentDto.setRecords(attendanceRecords);
        return studentDto;
    }

    public List<StudentDto> getStudents() {
        List<Student> students = studentRepository.findAll();
        return students.stream().map(StudentMapper::convertToDto).collect(Collectors.toList());
    }

    public void deleteStudent(int rollNo) throws InvalidRollNumberException {
        if (rollNo < 1) throw new InvalidRollNumberException("Roll number cannot be zero");
        studentRepository.deleteByRollNo(rollNo);
    }


}
