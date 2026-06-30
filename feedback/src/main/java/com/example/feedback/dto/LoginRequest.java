package com.example.feedback.dto;

import lombok.Data;

@Data
public class LoginRequest {
    private Long empId;
    private String password;
}
