package com.example.demo.crm.config;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {

    private static final String BEARER_AUTH = "bearerAuth";

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("CRM Avis - API Documentation")
                        .description(
                                "Hệ thống Quản lý Quan hệ Khách hàng (CRM) Avis.\n\n" +
                                "**Hướng dẫn sử dụng Swagger:**\n" +
                                "1. Gọi API `POST /api/auth/login` với username/password để lấy JWT Token.\n" +
                                "2. Sao chép giá trị `token` trong response.\n" +
                                "3. Nhấn nút **Authorize 🔓** ở góc trên bên phải.\n" +
                                "4. Dán token vào ô **Value** (không cần thêm chữ Bearer).\n" +
                                "5. Nhấn **Authorize** và đóng popup. Tất cả API sẽ hoạt động bình thường!"
                        )
                        .version("1.0.0")
                        .contact(new Contact()
                                .name("NMS Development Team")
                                .email("dev@nms.com"))
                        .license(new License().name("Private").url("#"))
                )
                .components(new Components()
                        .addSecuritySchemes(BEARER_AUTH, new SecurityScheme()
                                .type(SecurityScheme.Type.HTTP)
                                .scheme("bearer")
                                .bearerFormat("JWT")
                                .description("Dán JWT Token nhận được từ API /api/auth/login vào đây"))
                )
                .addSecurityItem(new SecurityRequirement().addList(BEARER_AUTH));
    }
}
