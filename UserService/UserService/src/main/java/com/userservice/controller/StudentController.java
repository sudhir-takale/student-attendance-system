package com.userservice.controller;

import com.userservice.dto.StudentDto;
import com.userservice.service.StudentService;
import com.userservice.service.exception.InvalidRollNumberException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin("*")
@RestController
@RequestMapping("/api")
public class StudentController {

    private final StudentService studentService;

    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @PostMapping("/students")
    public ResponseEntity<StudentDto> create(@RequestBody StudentDto studentDto) {
        StudentDto responseDto = studentService.create(studentDto);
        return new ResponseEntity<>(responseDto, HttpStatus.CREATED);
    }


    @GetMapping("/students/{rollNo}")
    public StudentDto getStudent(@PathVariable int rollNo) {
        return studentService.getStudentBy(rollNo);

    }

    @GetMapping("/students")
    public List<StudentDto> getStudents() {
        return studentService.getStudents();
    }

    @DeleteMapping("/students/{rollNo}")
    public ResponseEntity<String> deleteStudentBy(@PathVariable int rollNo) throws InvalidRollNumberException {
        studentService.deleteStudent(rollNo);
        return ResponseEntity.ok().body("Student deleted successfully");
    }

    @ExceptionHandler(InvalidRollNumberException.class)
    public ResponseEntity<String> invalidStudentIdExceptionHandler(InvalidRollNumberException e) {
        return ResponseEntity.badRequest().body(e.getMessage());
    }

}
