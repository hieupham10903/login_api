package org.example.login.service;

import org.example.login.entity.Department;
import org.example.login.entity.Position;
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

        @Autowired
        private UserService userService;

        public Salary saveSalary(Salary salary) {
            User user = userService.findById(salary.getUserId())
                    .orElseThrow(() -> new IllegalStateException("User not found"));

            String departmentName = user.getDepartment();
            String positionName = user.getPosition();

            Department department = userService.findDepartmentByName(departmentName)
                    .orElseThrow(() -> new IllegalStateException("Department not found"));
            int salaryBase = department.getSalaryBase();

            Position position = userService.findPositionByName(positionName)
                    .orElseThrow(() -> new IllegalStateException("Position not found"));
            float multiplier = position.getMultiplier();

            Long calculatedSalary = (long) (salaryBase * multiplier);
            salary.setSalary(calculatedSalary);

            Long totalSalary = calculatedSalary + (salary.getBonus() != null ? salary.getBonus() : 0) - (salary.getFine() != null ? salary.getFine() : 0);
            salary.setTotalSalary(totalSalary);

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

