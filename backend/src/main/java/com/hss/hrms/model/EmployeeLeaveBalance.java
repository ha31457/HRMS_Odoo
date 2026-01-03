package com.hss.hrms.model;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "employee_leave_balance",
        indexes = {
                @Index(name = "idx_elb_employee", columnList = "employee_id"),
                @Index(name = "idx_elb_year", columnList = "year")
        },
        uniqueConstraints = {
                @UniqueConstraint(name = "unique_employee_leave_year",
                        columnNames = {"employee_id", "leave_type_id", "year"})
        }
)
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class EmployeeLeaveBalance {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "balance_id")
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "employee_id", nullable = false)
    private Employee employee;

    @ManyToOne
    @JoinColumn(name = "leave_type_id", nullable = false)
    private LeaveType leaveType;

    @Column(nullable = false)
    private Integer year;

    @Column(name = "total_allocated", nullable = false, precision = 5, scale = 2)
    private BigDecimal totalAllocated;

    @Column(name = "used_days", precision = 5, scale = 2)
    private BigDecimal usedDays = BigDecimal.ZERO;

    @Column(name = "pending_days", precision = 5, scale = 2)
    private BigDecimal pendingDays = BigDecimal.ZERO;

    @Column(name = "available_days", nullable = false, precision = 5, scale = 2)
    private BigDecimal availableDays;

    @Column(name = "carried_forward", precision = 5, scale = 2)
    private BigDecimal carriedForward = BigDecimal.ZERO;

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