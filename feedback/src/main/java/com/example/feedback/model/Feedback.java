package com.example.feedback.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name="feedback")
public class Feedback {
   
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;
    private String name;
    @Column(unique = true, nullable = false)
    private Long empId;
    private String category;
    
    private String adminRemark;
    private String resolved;
    private LocalDateTime resolvedOn;

    @Column(columnDefinition="TEXT")
    private String message;
    private String department;
    private LocalDateTime submittedAt=LocalDateTime.now();
    private String status="Pending";
    


    //Getters and setter to save data 

    public Long getId(){
        return id;
    }

    public void setId(Long id){
        this.id=id;
    }
    
      public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

      public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public Long getEmpId(){
        return empId;
    }

    public void setEmpId(Long empId){
        this.empId=empId;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public LocalDateTime getSubmittedAt(){
        return submittedAt;
    }

    public void setSubmittedAt(LocalDateTime submittedAt){
        this.submittedAt=submittedAt;
    }
    public LocalDateTime getResolvedOn(){
        return resolvedOn;
    }

    public void setResolvedOn(LocalDateTime resolvedOn){
        this.resolvedOn=resolvedOn;
    }

    public String getStatus(){
        return status;
    }
    
    public void setStatus(String status){
        this.status=status;
    }

    public String getAdminRemark(){
        return adminRemark;
    }
    public void setAdminRemark(String adminRemark){
        this.adminRemark=adminRemark;

    }

    public String getResolved(){
        return resolved;
    }
    public void setResolved(String resolved){
        this.resolved=resolved;
    }
}
