package org.example.login.controller;

import org.example.login.entity.Attendance;
import org.example.login.entity.Salary;
import org.example.login.entity.User;
import org.example.login.service.AttendanceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/attendance")
public class AttendanceController {

    @Autowired
    private AttendanceService attendanceService;

    @GetMapping("/attendances")
    public List<Attendance> getAllAttendances() {
        return attendanceService.getAllAttendances();
    }

    @GetMapping("/search")
    public ResponseEntity<List<Attendance>> searchSalaries(@RequestParam(required = false) Long userId) {
        List<Attendance> attendances = attendanceService.getAttendanceByUserId(userId);
        return ResponseEntity.ok(attendances);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Attendance> getAttendanceById(@PathVariable Long id) {
        Optional<Attendance> attendance = attendanceService.findById(id);
        if (attendance.isPresent()) {
            return ResponseEntity.ok(attendance.get());
        }
        return ResponseEntity.notFound().build();
    }

    @GetMapping("/{userId}/{date}")
    public ResponseEntity<Attendance> getAttendance(@PathVariable Long userId, @PathVariable String date) {
        LocalDate localDate = LocalDate.parse(date);
        return attendanceService.getAttendanceByUserIdAndDate(userId, localDate)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping("/checkin")
    public ResponseEntity<Attendance> checkIn(@RequestParam Long userId) {
        try {
            Attendance attendance = attendanceService.checkIn(userId);
            return ResponseEntity.ok(attendance);
        } catch (IllegalStateException e) {
            return ResponseEntity.badRequest().body(null);
        }
    }

    @PostMapping("/checkout")
    public ResponseEntity<Attendance> checkOut(@RequestParam Long userId) {
        try {
            Attendance attendance = attendanceService.checkOut(userId);
            return ResponseEntity.ok(attendance);
        } catch (IllegalStateException e) {
            return ResponseEntity.badRequest().body(null);
        }
    }
}
