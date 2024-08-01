package org.example.login.service;

import org.example.login.entity.Salary;
import org.example.login.entity.User;
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

//    public Salary getSalaryByUserId(Long userId) {
//        return salaryRepository.findByUserId(userId);
//    }
}
