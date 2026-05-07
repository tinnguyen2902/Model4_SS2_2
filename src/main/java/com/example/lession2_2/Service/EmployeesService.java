package com.example.lession2_2.Service;

import com.example.lession2_2.Repository.EmployeesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EmployeesService {
        @Autowired
        EmployeesRepository er;

}