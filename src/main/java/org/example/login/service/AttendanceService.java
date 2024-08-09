package org.example.login.service;

import org.example.login.entity.Attendance;
import org.example.login.entity.Salary;
import org.example.login.entity.User;
import org.example.login.repository.AttendanceRepository;
import org.example.login.repository.SalaryRepository;
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

    @Autowired
    private SalaryRepository salaryRepository;

    @Autowired
    private SalaryService salaryService;


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

        if (attendance.getWorkingMinutes() < 600) {
            updatePenaltyCount(userId, today);
        }

        return attendanceRepository.save(attendance);
    }

    private void updatePenaltyCount(Long userId, LocalDate date) {
        LocalDate startOfMonth = date.withDayOfMonth(1);
        Optional<Salary> salaryOpt = salaryRepository.findByUserIdAndDate(userId, startOfMonth);

        Salary salary;
        if (!salaryOpt.isPresent()) {
            salary = new Salary();
            salary.setUserId(userId);
            salary.setDate(startOfMonth);
            salary.setPenaltyCount(1);
        } else {
            salary = salaryOpt.get();
            salary.setPenaltyCount(salary.getPenaltyCount() + 1);
        }
        salary.calculateFine();
        salaryService.saveSalary(salary);
    }

}
