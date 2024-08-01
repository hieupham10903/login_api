package org.example.login.controller;

import org.example.login.entity.Attendance;
import org.example.login.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;

@RestController
@RequestMapping("/api/attendance")
public class AttendanceController {

    @Autowired
    private AttendanceService attendanceService;

    @GetMapping("/{userId}/{date}")
    public ResponseEntity<Attendance> getAttendance(@PathVariable Long userId, @PathVariable String date) {
        LocalDate localDate = LocalDate.parse(date);
        return attendanceService.getAttendanceByUserIdAndDate(userId, localDate)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping("/checkin")
    public ResponseEntity<Attendance> checkIn(@RequestBody Long userId) {
        try {
            Attendance attendance = attendanceService.checkIn(userId);
            return ResponseEntity.ok(attendance);
        } catch (IllegalStateException e) {
            return ResponseEntity.badRequest().body(null);
        }
    }

    @PostMapping("/checkout")
    public ResponseEntity<Attendance> checkOut(@RequestBody Long userId) {
        try {
            Attendance attendance = attendanceService.checkOut(userId);
            return ResponseEntity.ok(attendance);
        } catch (IllegalStateException e) {
            return ResponseEntity.badRequest().body(null);
        }
    }
}
