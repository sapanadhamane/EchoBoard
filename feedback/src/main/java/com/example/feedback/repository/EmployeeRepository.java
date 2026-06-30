package com.example.feedback.repository;


import java.util.Optional;

import com.example.feedback.model.Employee;


//import org.springframework.boot.autoconfigure.security.SecurityProperties.User;
import org.springframework.data.jpa.repository.JpaRepository;


    public interface EmployeeRepository extends JpaRepository <Employee, Long>{
    Optional<Employee> findByEmpId(long empId);

    
}
    

