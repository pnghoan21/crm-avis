package com.example.demo.crm.controller;

import com.example.demo.crm.security.JwtUtil;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@Tag(name = "Auth - Xác thực", description = "Đăng nhập để lấy JWT Token. Sau khi có token, nhấn nút Authorize ở góc trên bên phải và dán token vào.")
@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthenticationManager authenticationManager;
    private final UserDetailsService userDetailsService;
    private final JwtUtil jwtUtil;

    @Operation(
        summary = "Đăng nhập lấy JWT Token",
        description = "Truyền vào username và password. Nếu thành công, API trả về một JWT Token có hiệu lực 24 giờ. " +
                      "**Cách dùng trên Swagger:** Sao chép token nhận được, nhấn nút Authorize ở góc phải, " +
                      "dán vào ô Value theo định dạng: `Bearer <token>`"
    )
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest request) {
        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword())
            );
        } catch (BadCredentialsException e) {
            return ResponseEntity.status(401).body(Map.of("error", "Username hoặc password không đúng"));
        }

        UserDetails userDetails = userDetailsService.loadUserByUsername(request.getUsername());
        String token = jwtUtil.generateToken(userDetails);

        return ResponseEntity.ok(Map.of(
                "token", token,
                "type", "Bearer",
                "username", userDetails.getUsername(),
                "roles", userDetails.getAuthorities().toString()
        ));
    }

    @Data
    public static class LoginRequest {
        private String username;
        private String password;
    }
}
