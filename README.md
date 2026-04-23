# Spring Boot CRM Module (v2.0.1)

Dự án này đã được tái cấu trúc để bao gồm mô-đun **Customer Management (CRM)** hoàn chỉnh dựa trên kỹ năng được cung cấp.

## Cấu trúc dự án
```
com.example.demo.crm
├── controller/      # REST API Endpoints
├── service/         # Logic nghiệp vụ & Phân quyền
├── repository/      # Giao tiếp Database (JPA)
├── entity/          # Các thực thể (User, Customer, History, Complaint)
├── dto/             # Đối tượng trao đổi dữ liệu
├── mapper/          # Chuyển đổi Entity <-> DTO (MapStruct)
├── enums/           # Các Enum hệ thống
└── security/        # Cấu hình bảo mật (Admin/Sales)
```

## Các tính năng chính
- **Quản lý khách hàng 360°**: Thông tin chi tiết, lịch sử chăm sóc và khiếu nại.
- **Phân quyền dựa trên vai trò**:
  - **ADMIN**: Quản lý toàn bộ hệ thống, tạo tài khoản Sales, chuyển giao khách hàng.
  - **SALES**: Chỉ quản lý khách hàng được gán cho mình.
- **Tự động hóa**: Tự động tạo khách hàng từ Deal thành công.
- **Tìm kiếm & Phân trang**: Hỗ trợ lọc theo loại khách hàng, Sales phụ trách và từ khóa.

## API Endpoints (Ví dụ)
- `GET /api/v1/customers`: Liệt kê khách hàng (có phân trang & lọc).
- `GET /api/v1/customers/{id}`: Xem chi tiết 360° khách hàng.
- `POST /api/v1/customers/{id}/care`: Thêm lịch sử chăm sóc.
- `POST /api/v1/customers/{id}/complaints`: Thêm khiếu nại.

## Hướng dẫn chạy & Kiểm tra
1. **Build dự án**: `mvn clean package`
2. **Chạy ứng dụng**: `mvn spring-boot:run`
3. **Cơ sở dữ liệu**: Dự án sử dụng **PostgreSQL**.
   - Chạy Docker: `docker run -d --name crm-postgres -e POSTGRES_DB=crm_demo -e POSTGRES_USER=postgres -e POSTGRES_PASSWORD=123456 -p 5432:5432 postgres:13`
   - Database: `crm_demo`, User: `postgres`, Pass: `123456`.
   - Khởi tạo bảng: Sử dụng file `init.sql` được cung cấp trong thư mục gốc.
4. **Bảo mật**: Hệ thống sử dụng HTTP Basic Auth.

---
*Ghi chú: Mô-đun này tuân thủ các quy tắc Clean Code và thiết kế phân lớp.*
