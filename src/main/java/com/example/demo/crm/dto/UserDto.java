package com.example.demo.crm.dto;

import com.example.demo.crm.enums.Role;
import lombok.Data;

@Data
public class UserDto {
    private Long id;
    private String username;
    private Role role;
    private String status;
}
