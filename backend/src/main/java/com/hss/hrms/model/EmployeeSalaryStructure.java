package com.hss.hrms.model;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "employee_salary_structure", indexes = {
        @Index(name = "idx_ess_employee", columnList = "employee_id"),
        @Index(name = "idx_ess_current", columnList = "is_current"),
        @Index(name = "idx_ess_effective", columnList = "effective_from, effective_to")
})
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class EmployeeSalaryStructure {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "salary_structure_id")
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "employee_id", nullable = false)
    private Employee employee;

    @Column(name = "effective_from", nullable = false)
    private LocalDate effectiveFrom;

    @Column(name = "effective_to")
    private LocalDate effectiveTo;

    @Column(name = "ctc_annual", nullable = false, precision = 12, scale = 2)
    private BigDecimal ctcAnnual;

    @Column(name = "basic_salary", nullable = false, precision = 12, scale = 2)
    private BigDecimal basicSalary;

    @Column(name = "is_current")
    private Boolean isCurrent = true;

    @Column(name = "created_at", updatable = false)
    private LocalDateTime createdAt;

    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    @ManyToOne
    @JoinColumn(name = "created_by", nullable = false)
    private Users createdBy;

    @OneToMany(mappedBy = "salaryStructure", cascade = CascadeType.ALL)
    private List<SalaryStructureDetail> details;

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
