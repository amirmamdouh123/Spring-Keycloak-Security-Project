//package com.after.yom.Services;
//
//import com.after.yom.Repos.EmployeeRepo;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//import org.springframework.security.authentication.AuthenticationManager;
//
//import java.util.List;
//
//@Service
//public class EmployeeService {
//
//    @Autowired
//    private EmployeeRepo employeeRepo;
//
//
//    @Autowired
//    AuthenticationManager authenticationManager;
//
//    public List<Employee> getAll(){
//        return employeeRepo.findAll();
//    }
//
//    public Employee insertEmployee(Employee e){
//        return employeeRepo.save(e);
//    }
//
//
//    public Employee getEmployeeById(Long id){
//        return employeeRepo.getById(id);
//    }
//
////    public empProjection getProjectionById(Long id){
////        empProjection e=employeeRepo.getProjectionById(id);
////        return e;
////    }
//
//    public Employee createNewEmployee(Employee employee){
//        return employeeRepo.save(employee);
//    }
//
//
//}
