package org.example.login.controller;

import org.example.login.entity.Salary;
import org.example.login.entity.User;
import org.example.login.service.SalaryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api")
public class SalaryController {
    @Autowired
    private SalaryService salaryService;

    @GetMapping("/salaries")
    public List<Salary> getAllSalaries() {
        return salaryService.getAllSalaries();
    }

    @GetMapping("/salary/{id}")
    public ResponseEntity<Salary> getSalaryById(@PathVariable Long id) {
        Optional<Salary> salary = salaryService.findById(id);
        if (salary.isPresent()) {
            return ResponseEntity.ok(salary.get());
        }
        return ResponseEntity.notFound().build();
    }

    @PostMapping("/salary")
    public ResponseEntity<Salary> addSalary(@RequestBody Salary salary) {
        Salary savedSalary = salaryService.saveSalary(salary);
        return ResponseEntity.ok(savedSalary);
    }

    @PutMapping("/salary/{id}")
    public ResponseEntity<Salary> updateSalary(@PathVariable Long id, @RequestBody Salary salary) {
        Optional<Salary> existingSalary = salaryService.findById(id);
        if (existingSalary.isPresent()) {
            salary.setId(id);
            Salary updatedSalary = salaryService.saveSalary(salary);
            return ResponseEntity.ok(updatedSalary);
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/salary/{id}")
    public ResponseEntity<Void> deleteSalary(@PathVariable Long id) {
        salaryService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/salary/search")
    public ResponseEntity<List<Salary>> searchSalaries(@RequestParam(required = false) Long userId) {
        List<Salary> salaries = salaryService.searchByUserId(userId);
        return ResponseEntity.ok(salaries);
    }
}
