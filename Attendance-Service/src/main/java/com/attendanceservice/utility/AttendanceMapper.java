package com.attendanceservice.utility;

import com.attendanceservice.domain.model.AttendanceRecord;
import com.attendanceservice.dto.AttendanceDto;

public class AttendanceMapper {

    public static AttendanceDto convertToDto(AttendanceRecord saved) {
        return new AttendanceDto(saved.getRollNo(), saved.getDate(), saved.getStatus());
    }
}
