package com.attendanceservice.controller;

import com.attendanceservice.dto.AttendanceDto;
import com.attendanceservice.dto.Student;
import com.attendanceservice.service.AttendanceService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.Matchers.hasSize;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest

public class AttendanceControllerTest {

    @Autowired
    ObjectMapper objectMapper;

    @MockBean
    AttendanceService attendanceService;

    @Autowired
    MockMvc mockMvc;

    AttendanceDto attendanceDto;

    @BeforeEach
    void setUp() {
        attendanceDto = new AttendanceDto(1, LocalDate.now(), 'P');
    }

    @Test
    void shouldBeAbleToCreateNewAttendance() throws Exception {
        when(attendanceService.create(attendanceDto)).thenReturn(attendanceDto);

        this.mockMvc.perform(MockMvcRequestBuilders.post("/api/attendance")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(attendanceDto)))
                .andExpect(status().isOk());
    }

    @Test
    void shouldBeAbleToGetAttendanceSheet() throws Exception {

        List<AttendanceDto> t = new java.util.ArrayList<>();
        t.add(attendanceDto);
        t.add(attendanceDto);
        when(attendanceService.getAttendanceSheet()).thenReturn(t);

        this.mockMvc.perform(MockMvcRequestBuilders.get("/api/attendance")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(2)));
    }


    @Test
    void shouldBeAbleToGetSheetToMarkAttendance() throws Exception {
        List<Student> students = new ArrayList<>();
        students.add(new Student());
        students.add(new Student());
        when(attendanceService.markAttendance()).thenReturn(students);

        this.mockMvc.perform(MockMvcRequestBuilders.get("/api/mark-attendance")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(2)));
    }


    @Test
    void shouldBeAbleToGetAttendanceByRollNumber() throws Exception {
        when(attendanceService.getRecordOf(anyInt())).thenReturn(List.of(attendanceDto, attendanceDto));

        this.mockMvc.perform(MockMvcRequestBuilders.get("/api/attendance/student/1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(2)));
    }





}
