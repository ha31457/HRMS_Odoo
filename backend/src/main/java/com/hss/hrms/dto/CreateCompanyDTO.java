package com.hss.hrms.dto;

import com.hss.hrms.model.CompanyDetails;
import jakarta.persistence.Column;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class CreateCompanyDTO extends CompanyDetails {
    private Integer id;
    private String companyName;
    private String companyLocation;
    private String logoUrl;
    private String registrationNumber;
    private String taxId;
    private String addressLine1;
    private String addressLine2;
    private String city;
    private String state;
    private String postalCode;
    private String country;
    private String phone;
    private String email;
    private String website;
    private LocalDate establishedDate;
    private Boolean isActive = true;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
