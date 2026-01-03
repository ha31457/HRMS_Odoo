package com.hss.hrms.model;
import com.hss.hrms.enums.EmployeeStatus;
import com.hss.hrms.enums.EmploymentType;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "employee_job_details", indexes = {
        @Index(name = "idx_ejd_department", columnList = "department_id"),
        @Index(name = "idx_ejd_designation", columnList = "designation_id"),
        @Index(name = "idx_ejd_manager", columnList = "reporting_manager_id"),
        @Index(name = "idx_ejd_status", columnList = "employee_status")
})
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class EmployeeJobDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "job_detail_id")
    private Integer id;

    @OneToOne
    @JoinColumn(name = "employee_id", unique = true, nullable = false)
    private Employee employee;

    @ManyToOne
    @JoinColumn(name = "department_id", nullable = false)
    private Department department;

    @ManyToOne
    @JoinColumn(name = "designation_id", nullable = false)
    private Designation designation;

    @ManyToOne
    @JoinColumn(name = "reporting_manager_id")
    private Employee reportingManager;

    @Enumerated(EnumType.STRING)
    @Column(name = "employment_type", nullable = false)
    private EmploymentType employmentType;

    @Column(name = "date_of_joining", nullable = false)
    private LocalDate dateOfJoining;

    @Column(name = "date_of_leaving")
    private LocalDate dateOfLeaving;

    @Column(name = "probation_end_date")
    private LocalDate probationEndDate;

    @Column(name = "work_location", length = 100)
    private String workLocation;

    @Enumerated(EnumType.STRING)
    @Column(name = "employee_status")
    private EmployeeStatus employeeStatus = EmployeeStatus.Active;

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
