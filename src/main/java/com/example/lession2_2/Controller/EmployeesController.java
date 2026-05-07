package com.example.lession2_2.Controller;

import com.example.lession2_2.Model.Emloyees;
import com.example.lession2_2.Service.EmployeesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/employees")
public class EmployeesController {
     @Autowired
     EmployeesService es;
     // danh sách
     private List<Emloyees> emloyeesList = new ArrayList<>();
     // tạo dữ liệu
     public EmployeesController(){
          emloyeesList.add(new Emloyees(1,"Nguyen Van A","abc@gmail.com","HR"));
          emloyeesList.add(new Emloyees(2,"Nguyen Van A2","abc2@gmail.com","Sale"));
          emloyeesList.add(new Emloyees(3,"Nguyen Van A3","abc4@gmail.com","IT"));
     }
     // GET
     @GetMapping
     public List<Emloyees> getAllEmployees(){
          return emloyeesList;

     }

}