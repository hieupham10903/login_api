package org.example.login.service;

import org.example.login.entity.Department;
import org.example.login.entity.Position;
import org.example.login.entity.Salary;
import org.example.login.entity.User;
import org.example.login.repository.DepartmentRepository;
import org.example.login.repository.PositionRepository;
import org.example.login.repository.SalaryRepository;
import org.example.login.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private SalaryRepository salaryRepository;

    @Autowired
    private DepartmentRepository departmentRepository;

    @Autowired
    private PositionRepository positionRepository;

    public Optional<User> findByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    public Optional<User> findById(Long id) {
        return userRepository.findById(id);
    }

    public User saveUser(User user) {
        return userRepository.save(user);
    }

    public void deleteById(Long id) {
        userRepository.deleteById(id);
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public List<User> searchByName(String name) {
        return userRepository.findByFullnameContainingIgnoreCase(name);
    }

    public Optional<Department> findDepartmentByName(String name) {
        return departmentRepository.findById(name);
    }

    public Optional<Position> findPositionByName(String name) {
        return positionRepository.findById(name);
    }

//    public Salary getSalaryByUserId(Long userId) {
//        return salaryRepository.findByUserId(userId);
//    }
}
