package com.example.feedback.dto;

import com.example.feedback.model.Employee;

public class EmployeeDto {
    
    private Long empId;
    private String profileName;
    private String department;
    private String email;

    public EmployeeDto(Employee emp) {
        this.empId = emp.getEmpId();
        this.profileName = emp.getName();
        this.department = emp.getDepartment();
        this.email = emp.getEmail(); 
    }

    // Getters
    public Long getEmpId() { return empId; }
    public String getProfileName() { return profileName; }
    public String getDepartment() { return department; }
    public String getEmail() { return email; }
}

    

