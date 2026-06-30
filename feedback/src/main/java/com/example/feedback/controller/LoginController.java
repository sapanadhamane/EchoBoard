package com.example.feedback.controller;


import java.util.Optional;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;


import com.example.feedback.dto.LoginRequest;
import com.example.feedback.dto.LoginResponse;
import com.example.feedback.model.Employee;
import com.example.feedback.model.User;
import com.example.feedback.repository.EmployeeRepository;
import com.example.feedback.repository.UserRepository;


 @CrossOrigin(origins = "http://127.0.0.1:5500") //Use the frontend domain to get requests (REST api)
 @RestController
 @RequestMapping("/api")

   
public class LoginController {

     @Autowired
    private UserRepository userRepository;

    @Autowired
    private EmployeeRepository employeeRepository;

     @PostMapping("/login")
     public ResponseEntity<LoginResponse>login(@RequestBody LoginRequest loginRequest){
        Optional<User> userOpt= userRepository.findByEmpId(loginRequest.getEmpId());

        if(userOpt.isPresent()){
            User user=userOpt.get();

            if(user.getPassword().equals(loginRequest.getPassword())){
                Long empId=user.getEmpId();
                Optional<Employee>empOpt=employeeRepository.findByEmpId(empId);
                if(empOpt.isPresent()){
                    Employee employee=empOpt.get();
                
                return ResponseEntity.ok(new LoginResponse(true, "Login Successful",employee.getEmpId(), employee.getRole()));
                }
            }

        }
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(new LoginResponse(false, "Invalid Employee ID or password",null,null));
     }


    // @PostMapping("/upload/{empId}")
    // public ResponseEntity<?>uploadPhoto(@PathVariable Long empId, @RequestParam("file") MultipartFile file) throws IOException{
    //     Employee emp=employeeRepository.findByEmpId(empId).orElseThrow(null);
    //     emp.setPhotoUrl(file.getBytes());
    //     employeeRepository.save(emp);
    //     return ResponseEntity.ok("Photo uploaded!");

    // }
}
