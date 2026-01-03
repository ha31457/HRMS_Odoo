package com.hss.hrms.model;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "employee_bank_details", indexes = {
        @Index(name = "idx_ebd_employee", columnList = "employee_id"),
        @Index(name = "idx_ebd_pan", columnList = "pan_number"),
        @Index(name = "idx_ebd_uan", columnList = "uan_number")
})
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class EmployeeBankDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "bank_detail_id")
    private Integer id;

    @OneToOne
    @JoinColumn(name = "employee_id", unique = true, nullable = false)
    private Employee employee;

    @Column(name = "bank_name", nullable = false, length = 200)
    private String bankName;

    @Column(name = "account_number", nullable = false, length = 50)
    private String accountNumber;

    @Column(name = "ifsc_code", nullable = false, length = 11)
    private String ifscCode;

    @Column(name = "pan_number", nullable = false, length = 10)
    private String panNumber;

    @Column(name = "uan_number", length = 12)
    private String uanNumber;

    @Column(name = "account_holder_name", nullable = false, length = 200)
    private String accountHolderName;

    @Column(name = "branch_name", length = 200)
    private String branchName;

    @Column(name = "is_verified")
    private Boolean isVerified = false;

    @Column(name = "created_at", updatable = false)
    private LocalDateTime createdAt;

    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    @PrePersist
    protected void onCreate() {
        createdAt = LocalDateTime.now();
        updatedAt = LocalDateTime.now();
    }

    @PreUpdate
    protected void onUpdate() {
        updatedAt = LocalDateTime.now();
    }
}
