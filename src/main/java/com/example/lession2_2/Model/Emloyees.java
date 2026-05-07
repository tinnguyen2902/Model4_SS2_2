package com.example.lession2_2.Model;

import org.springframework.beans.factory.annotation.Autowired;

public class Emloyees {
    private Integer id;
    private String fullName;
    private String email;
    private String department;

    public Emloyees() {
    }

    public Emloyees(Integer id, String fullName, String email, String department) {
        this.id = id;
        this.fullName = fullName;
        this.email = email;
        this.department = department;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }
}