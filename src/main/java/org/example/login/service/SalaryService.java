package org.example.login.service;

import org.example.login.entity.Salary;
import org.example.login.entity.User;
import org.example.login.repository.SalaryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SalaryService {
    @Autowired
    private SalaryRepository salaryRepository;

    public Salary saveSalary(Salary salary) {
        return salaryRepository.save(salary);
    }

    public void deleteById(Long id) {
        salaryRepository.deleteById(id);
    }

    public List<Salary> getAllSalaries() {
        return salaryRepository.findAll();
    }

    public List<Salary> searchByUserId(Long userId) {
        return salaryRepository.findByUserId(userId);
    }

    public Optional<Salary> findById(Long id) {
        return salaryRepository.findById(id);
    }
}
