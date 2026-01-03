package com.hss.hrms.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateDesignationDTO {
    private Integer id;
    private String designationName;
    private Integer designationLevel;
    private String description;
    private Boolean isActive = true;
    private LocalDateTime createdAt;
}
