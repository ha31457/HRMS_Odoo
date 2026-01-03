package com.hss.hrms.dto;

import lombok.*;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class CreateDepartmentDTO {
    private String departmentId;
    private String departmentName;
    private String departmentCode;
    private String description;
    private Boolean isActive = true;
    private LocalDateTime createdAt;
}
