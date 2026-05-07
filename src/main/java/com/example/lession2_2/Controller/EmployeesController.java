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
     //SS2.5
     // thêm mới
     @PostMapping
     public String addEmployee(@RequestBody Emloyees newE){
          // tự động tăng id
          int nextID = 1;
          if (!emloyeesList.isEmpty()){
               // tự động tăng từ người cuối thêm 1
               nextID = emloyeesList.get(emloyeesList.size()-1).getId()+ 1;
          }
          // gán ID mới
          newE.setId(nextID);
          // thêm nv mới
          emloyeesList.add(newE);
          return "Thêm mới ok id: " + nextID;
     }
     //cập nhật nhân viên
     @PutMapping("/{id}")
     public String updateEmployee(@PathVariable Integer id, @RequestBody Emloyees updatedE){
          // tìm nhân viên dự và pathvariable
          for ( int i = 0;i<emloyeesList.size();i++){
               Emloyees e = emloyeesList.get(i);
               if (e.getId() == id){
                    e.setFullName(updatedE.getFullName());
                    e.setEmail(updatedE.getEmail());
                    e.setDepartment(updatedE.getDepartment());
                    return "Cập nhật nv có id = " + id + "OK";
               }
          }
          return "Không tìm thấy nv có " + id;
     }
     // xóa
     @DeleteMapping("/{id}")
     public String deleteEmployee(@PathVariable Integer id){
          //duyệt để tìm id
          for ( int i = 0;i<emloyeesList.size();i++){
               Emloyees e = emloyeesList.get(i);
               if (e.getId() == id){
                    emloyeesList.remove(i);
                    return "Xóa ok" + id;
               }
          }
          // nếu k có id
          return "Không có nv có id là: " + id;
     }


}