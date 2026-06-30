package com.example.feedback.model;

import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;

@Entity
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false)
    private Long empId;
    private String name;
    private LocalDate joiningDate;
    private String department;
    @Lob
    private byte[] photoUrl;
    private String role;
    private String email;


     public Long getId(){
        return id;
    }
    public void setId(Long id){
        this.id=id;
    }

     public Long getEmpId(){
        return empId;
    }
    public void setEmpId(Long empId){
        this.empId=empId;
    }

     public String getName(){
        return name;
    }
    public void setName(String name){
        this.name=name;
    }
     public LocalDate getJoiningDate(){
        return joiningDate;
    }
    public void setJoiningDate(LocalDate joiningDate){
        this.joiningDate=joiningDate;
    }
     public String getDepartment(){
        return department;
    }
    public void setDepartment(String department){
        this.department=department;
    }
     public byte[] getPhotoUrl(){
        return photoUrl;
    }
    public void setPhotoUrl(byte[] photUrl){
        this.photoUrl=photUrl;
    }
     public String getRole(){
        return role;
    }
    public void setRole(String role){
        this.role=role;
    }
    public String getEmail(){
        return email;
    }
    public void setEmail(String email){
        this.email=email;
    }
}
