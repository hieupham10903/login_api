package org.example.login.entity;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table(name = "salary")
public class Salary {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "userId")
    private Long userId;

    @Column(name = "bonus")
    private Long bonus;

    @Column(name = "fine")
    private Long fine;

    @Column(name = "salary")
    private Long salary;

    @Column(name = "totalsalary")
    private Long totalSalary;

    @Column(name = "date")
    private LocalDate date;

    @Column(name = "penalty_count", nullable = false)
    private Integer penaltyCount = 0;

    public Salary() {}

    public Salary(Long userId, Long bonus, Long fine, Long salary, Long totalsalary, LocalDate date, Integer penaltyCount) {
        this.userId = userId;
        this.bonus = bonus;
        this.fine = fine;
        this.salary = salary;
        this.totalSalary = totalsalary;
        this.date = date;
        this.penaltyCount = penaltyCount;
    }

    public Long getTotalSalary() {
        return totalSalary;
    }

    public void setTotalSalary(Long totalSalary) {
        this.totalSalary = totalSalary;
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

    public Long getSalary() {
        return salary;
    }

    public void setSalary(Long salary) {
        this.salary = salary;
    }

    public Long getBonus() {
        return bonus;
    }

    public void setBonus(Long bonus) {
        this.bonus = bonus;
    }

    public Long getFine() {
        return fine;
    }

    public void setFine(Long fine) {
        this.fine = fine;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public Integer getPenaltyCount() {
        return penaltyCount;
    }

    public void setPenaltyCount(Integer penaltyCount) {
        this.penaltyCount = penaltyCount;
    }

    public void calculateFine() {
        this.fine = this.penaltyCount * 50000L;
    }

    @Override
    public String toString() {
        return "Salary{" +
                "id=" + id +
                ", userId=" + userId +
                ", bonus=" + bonus +
                ", fine=" + fine +
                ", salary=" + salary +
                ", totalSalary=" + totalSalary +
                ", date=" + date +
                ", penaltyCount=" + penaltyCount +
                '}';
    }
}
