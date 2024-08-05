package org.example.login.service;

import org.example.login.entity.Attendance;
import org.example.login.entity.Salary;
import org.example.login.entity.User;
import org.example.login.repository.AttendanceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class AttendanceService {

    @Autowired
    private AttendanceRepository attendanceRepository;

    private final ZoneId timeZone = ZoneId.of("Asia/Ho_Chi_Minh");

    public Optional<Attendance> getAttendanceByUserIdAndDate(Long userId, LocalDate date) {
        return attendanceRepository.findByUserIdAndDate(userId, date);
    }

    public List<Attendance> getAllAttendances() {
        return attendanceRepository.findAll();
    }

    public List<Attendance> getAttendanceByUserId(Long id) {
        return attendanceRepository.findByUserId(id);
    }

    public Optional<Attendance> findById(Long id) {
        return attendanceRepository.findById(id);
    }

    public Attendance checkIn(Long userId) {
        LocalDate today = LocalDate.now();
        Optional<Attendance> attendanceOpt = attendanceRepository.findByUserIdAndDate(userId, today);

        if (attendanceOpt.isPresent()) {
            throw new IllegalStateException("User has already checked in today.");
        }

        ZonedDateTime nowZoned = ZonedDateTime.now(timeZone);
        LocalDateTime now = nowZoned.toLocalDateTime();
        Attendance attendance = new Attendance(userId, today, now, null, null);
        return attendanceRepository.save(attendance);
    }

    public Attendance checkOut(Long userId) {
        LocalDate today = LocalDate.now();
        Attendance attendance = attendanceRepository.findByUserIdAndDate(userId, today)
                .orElseThrow(() -> new IllegalStateException("User must check in before checking out."));

        if (attendance.getCheckOut() != null) {
            throw new IllegalStateException("User has already checked out today.");
        }

        ZonedDateTime nowZoned = ZonedDateTime.now(timeZone);
        LocalDateTime now = nowZoned.toLocalDateTime();
        attendance.setCheckOut(now);
        attendance.calculateWorkingMinutes();
        return attendanceRepository.save(attendance);
    }
}
