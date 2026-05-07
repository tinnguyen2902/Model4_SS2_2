package com.example.lession2_2.Controller;

import com.example.lession2_2.Model.Emloyees;
import com.example.lession2_2.Model.EmployeeFilter;
import com.example.lession2_2.Service.EmployeesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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
          emloyeesList.add(new Emloyees(4,"Le Van A3","abc41@gmail.com","HR"));
          emloyeesList.add(new Emloyees(5,"Tran Van B3","abc42@gmail.com","Sale"));
          emloyeesList.add(new Emloyees(6,"Phan Van C3","abc43@gmail.com","IT"));
     }
     // GET
     @GetMapping
     public List<Emloyees> getAllEmployees(){
          return emloyeesList;
     }
     //SS2_4 tìm kiếm theo id=> dùng @PathVariable
     @GetMapping("/{id}")
     public Object getEmployeeById(@PathVariable int id){
          //duyệt qua for để tìm kiết
          for (Emloyees e : emloyeesList){
               if (e.getId() == id){
                    return e;
               }
          }
          return "ID " +  id  + " Không tồn tại" ;
     }
     //SS2_4 tifm kiếm theo tên => dùng @RepuestParam
     @GetMapping("/search")
     public List<Emloyees> getEmployeeByName(@RequestParam String FullName ){
          // tạo ds rỗng chứa các giá trị tồn tại
          List<Emloyees> result = new ArrayList<>();
          for ( int i = 0;i<emloyeesList.size();i++){
               Emloyees e = emloyeesList.get(i);
               if (e.getFullName().toLowerCase().contains(FullName.toLowerCase())){
                    result.add(e);
               }
          }
          return result;  // nếu không có trả về ds rỗng
     }
     //SS2_4 tìm kiếm nhân viên bằng tên & phòng dùng @ModelAttribute
     @GetMapping("/filter")
     public List<Emloyees> filterEmployee(@ModelAttribute EmployeeFilter filter){
          // tạo bảng rỗng
          List<Emloyees> result = new ArrayList<>();
          //dùng for lặp
          for ( int i = 0;i<emloyeesList.size();i++){
               Emloyees e = emloyeesList.get(i);
               boolean matchName = true;
               boolean matchDepartment = true;
               // check tên
               if (filter.getName() != null && !filter.getName().isEmpty()){
                    if (!e.getFullName().toLowerCase().contains(filter.getName().toLowerCase())){
                         matchName = false;
                    }
               }
               // check phòng ban
               if (filter.getDepartment() != null && !filter.getDepartment().isEmpty()){
                    // nếu phòng đó k chứa từ đó thì false
                    if (!e.getDepartment().toLowerCase().contains(filter.getDepartment().toLowerCase())){
                         matchDepartment = false;
                    }
          }
               // nếu tên + phòng OK
               if (matchName && matchDepartment){
                    result.add(e);
               }
          }
          // nếu NG
          return result;
     }


}