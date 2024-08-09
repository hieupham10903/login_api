package org.example.login.repository;

import org.example.login.entity.Salary;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface SalaryRepository extends JpaRepository<Salary, Long> {
//    Salary findByUserId(Long userId);
    List<Salary> findByUserId(Long UserId);
    Optional<Salary> findByUserIdAndDate(Long userId, LocalDate date);
}
