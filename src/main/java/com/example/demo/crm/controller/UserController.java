package com.example.demo.crm.controller;

import com.example.demo.crm.entity.User;
import com.example.demo.crm.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "Users - Người dùng", description = "Quản lý người dùng trong hệ thống (nhân viên, quản trị viên).")
@RestController
@RequestMapping("/api/v1/users")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @Operation(summary = "Tạo mới người dùng", description = "Tạo một người dùng mới trong hệ thống.")
    @PostMapping
    public User createUser(@RequestBody User user) {
        return userService.createUser(user);
    }

    @Operation(summary = "Lấy danh sách người dùng", description = "Lấy danh sách tất cả người dùng trong hệ thống. Chỉ dành cho Admin.")
    @GetMapping
    @PreAuthorize("hasRole('ADMIN')")
    public List<User> getUsers() {
        return userService.getAllUsers();
    }
}
