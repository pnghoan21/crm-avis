package com.example.demo.crm.dto;

import com.example.demo.crm.enums.Role;
import lombok.Data;

/**
 * DTO trả về thông tin cơ bản của một người dùng trong hệ thống.
 * Dùng trong các API quản lý người dùng và phân quyền.
 * Không chứa thông tin nhạy cảm như mật khẩu.
 */
@Data
public class UserDto {

    /** ID định danh duy nhất của người dùng. */
    private Long id;

    /** Tên đăng nhập của người dùng trong hệ thống. */
    private String username;

    /**
     * Vai trò (phân quyền) của người dùng.
     * Ví dụ: ADMIN (quản trị viên), SALES (nhân viên kinh doanh), MANAGER (trưởng nhóm).
     */
    private Role role;

    /**
     * Trạng thái hoạt động của tài khoản.
     * Ví dụ: ACTIVE (đang hoạt động), INACTIVE (đã vô hiệu hóa).
     */
    private String status;
}
