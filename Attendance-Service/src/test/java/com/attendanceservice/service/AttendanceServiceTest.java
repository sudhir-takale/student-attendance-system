package com.attendanceservice.service;

import com.attendanceservice.domain.model.AttendanceRecord;
import com.attendanceservice.dto.AttendanceDto;
import com.attendanceservice.repository.AttendanceRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class AttendanceServiceTest {

    @Mock
    private AttendanceRepository attendanceRepository;

    @InjectMocks
    private AttendanceService attendanceService;
    AttendanceRecord attendanceRecord;

    AttendanceDto attendanceDto;

    @BeforeEach
    void setUp() {
        attendanceRecord = AttendanceRecord.builder()
                .date(LocalDate.now())
                .rollNo(1)
                .status('P')
                .build();

        attendanceDto = new AttendanceDto(1, LocalDate.now(), 'P');
    }

    @Test
    void shouldBeAbleToCreateANewRecord() {
        when(attendanceRepository.save(attendanceRecord)).thenReturn(attendanceRecord);

        // act
        AttendanceDto response = attendanceService.create(attendanceDto);

        // act
        assertEquals(1, response.getRollNo());
    }


    @Test
    void shouldBeAbleToGetRecordOfStudentById() {
        when(attendanceRepository.findByRollNo(1)).thenReturn(Arrays.asList(attendanceRecord, attendanceRecord));

        List<AttendanceDto> result = attendanceService.getRecordOf(1);

        assertEquals(2, result.size());
        assertEquals(1, result.get(0).getRollNo());
    }
}