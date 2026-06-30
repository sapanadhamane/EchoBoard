package com.example.feedback.model;

import jakarta.persistence.*;

@Entity
@Table(name="users")
public class User {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;
    private String username;
    private String password;
    private String role;
     @Column(unique = true, nullable = false)
    private Long empId;
    private String department;



    public Long getId(){
        return id;
    }
    public void setId(Long id){
        this.id=id;
    }

    public String getUsername(){
        return username;
    }
    public void setUsername(String username){
        this.username=username;
    }

    public String getPassword(){
        return password;
    }
    public void setPassword(String password){
        this.password=password;
    }

    public String getRole(){
        return role;
    }
    public void setRole(String role){
        this.role=role;
    }

    //Add employee ID to link two tables- Employee and user

     public Long getEmpId(){
        return empId;
    }
    public void setEmpId(Long empId){
        this.empId=empId;
    }
     public String getDepartment(){
        return department;
    }
    public void setDepartment(String department){
        this.department=department;
    }




}