package com.example.demo.crm.controller;

import com.example.demo.crm.dto.LostReasonDTO;
import com.example.demo.crm.service.LostReasonService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;
import java.util.List;

@Tag(name = "Lost Reasons - Lý do thất bại", description = "Quản lý danh mục các lý do thất bại chuẩn hóa. Đây là dữ liệu master để buộc nhân viên lựa chọn khi chuyển Deal sang trạng thái LOST, đảm bảo tính nhất quán trong phân tích.")
@RestController
@RequestMapping("/api/lost-reasons")
@RequiredArgsConstructor
public class LostReasonController {

    private final LostReasonService service;

    @Operation(
        summary = "Lấy danh sách lý do thất bại",
        description = "Trả về toàn bộ danh mục lý do thất bại đã được chuẩn hóa, dùng để hiển thị lên form dropdown khi nhân viên chuyển Deal sang LOST."
    )
    @GetMapping
    public ResponseEntity<List<LostReasonDTO>> getAll() {
        return ResponseEntity.ok(service.getAll());
    }

    @Operation(
        summary = "Tạo mới lý do thất bại",
        description = "Thêm một lý do thất bại mới vào danh mục chuẩn hóa. Chỉ Admin nên được sử dụng API này để đảm bảo dữ liệu báo cáo nhất quán."
    )
    @PostMapping
    public ResponseEntity<LostReasonDTO> create(@Valid @RequestBody LostReasonDTO dto) {
        return ResponseEntity.ok(service.create(dto));
    }

    @Operation(
        summary = "Cập nhật lý do thất bại",
        description = "Chỉnh sửa tên hoặc mô tả của một lý do thất bại đã tồn tại theo ID."
    )
    @PutMapping("/{id}")
    public ResponseEntity<LostReasonDTO> update(
            @Parameter(description = "ID lý do thất bại cần cập nhật") @PathVariable Long id,
            @Valid @RequestBody LostReasonDTO dto) {
        return ResponseEntity.ok(service.update(id, dto));
    }

    @Operation(
        summary = "Xóa lý do thất bại",
        description = "Xóa vĩnh viễn một lý do thất bại khỏi danh mục. Lưu ý: Nên kiểm tra xem lý do này có đang được tham chiếu bởi bất kỳ Deal nào không trước khi xóa."
    )
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(
            @Parameter(description = "ID lý do thất bại cần xóa") @PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
