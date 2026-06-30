package com.example.feedback.controller;



import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.http.HttpHeaders;
import com.example.feedback.model.Employee;
import com.example.feedback.repository.EmployeeRepository;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "*")
public class EmployeeController {

    @Autowired
    private EmployeeRepository employeeRepository;

 @PostMapping("/upload/{empId}")
    public ResponseEntity<?>uploadPhoto(@PathVariable Long empId, @RequestParam("file") MultipartFile file) throws IOException{
        Employee emp=employeeRepository.findByEmpId(empId).orElseThrow(()-> new RuntimeException("Employee not found"));
        emp.setPhotoUrl(file.getBytes());
        System.out.println("Uploading photo for: "+emp.getEmpId());
        employeeRepository.save(emp);
        return ResponseEntity.ok("Photo uploaded!");

    }

    @GetMapping("/photo/{empId}")
    public ResponseEntity<byte[]>getPhotoUrl(@PathVariable Long empId){
        Employee emp=employeeRepository.findByEmpId(empId).orElseThrow(()-> new RuntimeException("Employee not found"));
        byte[] image=emp.getPhotoUrl();
        HttpHeaders headers=new HttpHeaders();
        headers.setContentType(MediaType.IMAGE_JPEG);
        return new ResponseEntity<>(image,headers,HttpStatus.OK);

    }

    @GetMapping("/employee/{empId}")
    public ResponseEntity<Employee>getEmployee(@PathVariable Long empId){
        System.out.println("Fetching employee with empId: " + empId);
        Employee emp=employeeRepository.findByEmpId(empId).orElseThrow(()-> new RuntimeException("Employe not found"));
        return ResponseEntity.ok(emp);
    }
    
}
