package org.example.login.entity;

import jakarta.persistence.*;
import jakarta.persistence.criteria.CriteriaBuilder;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZonedDateTime;

@Entity
@Table(name = "attendance")
public class Attendance {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "user_id")
    private Long userId;

    @Column(name = "date")
    private LocalDate date;

    @Column(name = "check_in")
    private LocalDateTime checkIn;

    @Column(name = "check_out")
    private LocalDateTime checkOut;

    @Column(name = "working_minutes")
    private Integer  workingMinutes;

    public Attendance() {}

    public Attendance(Long userId, LocalDate date, LocalDateTime checkIn, LocalDateTime checkOut, Integer workingMinutes) {
        this.userId = userId;
        this.date = date;
        this.checkIn = checkIn;
        this.checkOut = checkOut;
        this.workingMinutes = workingMinutes;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public LocalDateTime getCheckIn() {
        return checkIn;
    }

    public void setCheckIn(LocalDateTime checkIn) {
        this.checkIn = checkIn;
    }

    public LocalDateTime getCheckOut() {
        return checkOut;
    }

    public void setCheckOut(LocalDateTime checkOut) {
        this.checkOut = checkOut;
    }

    public Integer getWorkingMinutes() {
        return workingMinutes;
    }

    public void setWorkingMinutes(Integer workingMinutes) {
        this.workingMinutes = workingMinutes;
    }

    public void calculateWorkingMinutes() {
        if (checkIn != null && checkOut != null) {
            Duration duration = Duration.between(checkIn, checkOut);
            this.workingMinutes = (int) duration.toMinutes();
        }
    }
}
