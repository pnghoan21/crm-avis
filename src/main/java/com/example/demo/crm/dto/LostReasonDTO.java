package com.example.demo.crm.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import jakarta.validation.constraints.NotBlank;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class LostReasonDTO {
    private Long id;

    @NotBlank(message = "TÃªn lÃ½ do khÃ´ng Ä‘Æ°á»£c Ä‘á»ƒ trá»‘ng")
    private String reasonName;

    private String description;
}

