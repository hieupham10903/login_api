package org.example.login.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "department")
public class Department {
    @Id
    @Column(name = "name")
    private String name;

    @Column(name = "salarybase", nullable = false)
    private int salaryBase;

    public Department() {}

    public Department(String name, int salaryBase) {
        this.name = name;
        this.salaryBase = salaryBase;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getSalaryBase() {
        return salaryBase;
    }

    public void setSalaryBase(int salaryBase) {
        this.salaryBase = salaryBase;
    }
}
