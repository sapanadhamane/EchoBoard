package com.example.feedback.repository;

import java.util.Optional;


import com.example.feedback.model.User;

//import org.springframework.boot.autoconfigure.security.SecurityProperties.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository  extends JpaRepository<User,Long>{

Optional<User>findByEmpId(Long empId);

    
} 


