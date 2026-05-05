package com.example.demo.crm.entity;

import com.example.demo.crm.enums.Role;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import jakarta.persistence.*;

/**
 * Entity đại diện cho tài khoản người dùng trong hệ thống CRM.
 * Bao gồm thông tin đăng nhập và phân quyền.
 */
@Entity
@Table(name = "users")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {

    /** ID định danh duy nhất của người dùng, tự động sinh tăng dần. */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /** Tên đăng nhập của người dùng. Phải là duy nhất trong hệ thống và không được để trống. */
    @Column(unique = true, nullable = false)
    private String username;

    /** Mật khẩu đã được mã hóa (BCrypt) của người dùng. Không được để trống. */
    @Column(nullable = false)
    private String password;

    /**
     * Vai trò (phân quyền) của người dùng trong hệ thống.
     * Xác định quyền truy cập vào các chức năng của hệ thống.
     * Ví dụ: ADMIN, SALES, MANAGER.
     */
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Role role;

    /**
     * Trạng thái hoạt động của tài khoản.
     * Ví dụ: ACTIVE (đang hoạt động), INACTIVE (đã vô hiệu hóa).
     */
    private String status;
}
