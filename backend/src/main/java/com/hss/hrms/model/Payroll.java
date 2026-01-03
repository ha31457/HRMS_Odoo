package com.hss.hrms.model;

import com.hss.hrms.enums.PaymentStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "payroll",
        uniqueConstraints = @UniqueConstraint(
                columnNames = {"employee_id", "pay_period_year", "pay_period_month"}
        ))
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Payroll {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "payroll_id")
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "employee_id", nullable = false)
    private Employee employee;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "salary_structure_id", nullable = false)
    private EmployeeSalaryStructure salaryStructure;

    @Column(name = "pay_period_month", nullable = false)
    private Integer payPeriodMonth; // 1-12

    @Column(name = "pay_period_year", nullable = false)
    private Integer payPeriodYear;

    @Column(name = "gross_salary", nullable = false, precision = 12, scale = 2)
    private BigDecimal grossSalary;

    @Column(name = "total_deductions", nullable = false, precision = 12, scale = 2)
    private BigDecimal totalDeductions;

    @Column(name = "net_salary", nullable = false, precision = 12, scale = 2)
    private BigDecimal netSalary;

    @Column(name = "days_worked", nullable = false, precision = 5, scale = 2)
    private BigDecimal daysWorked;

    @Column(name = "days_absent", precision = 5, scale = 2)
    private BigDecimal daysAbsent = BigDecimal.ZERO;

    @Column(name = "days_on_leave", precision = 5, scale = 2)
    private BigDecimal daysOnLeave = BigDecimal.ZERO;

    @Column(name = "payment_date")
    private LocalDate paymentDate;

    @Enumerated(EnumType.STRING)
    @Column(name = "payment_status", nullable = false)
    private PaymentStatus paymentStatus = PaymentStatus.PENDING;

    @Column(name = "remarks", columnDefinition = "TEXT")
    private String remarks;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "processed_by")
    private Users processedBy;

    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt;

    // One-to-Many relationship with PayrollDetails
    @OneToMany(mappedBy = "payroll", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<PayrollDetail> payrollDetails;

    // Lifecycle callback
    @PrePersist
    protected void onCreate() {
        if (createdAt == null) {
            createdAt = LocalDateTime.now();
        }
        if (paymentStatus == null) {
            paymentStatus = PaymentStatus.PENDING;
        }
        if (daysAbsent == null) {
            daysAbsent = BigDecimal.ZERO;
        }
        if (daysOnLeave == null) {
            daysOnLeave = BigDecimal.ZERO;
        }
    }

    // Helper method to calculate net salary
    public void calculateNetSalary() {
        this.netSalary = this.grossSalary.subtract(this.totalDeductions);
    }

    // Helper method to add payroll detail
    public void addPayrollDetail(PayrollDetail detail) {
        payrollDetails.add(detail);
        detail.setPayroll(this);
    }

    // Helper method to remove payroll detail
    public void removePayrollDetail(PayrollDetail detail) {
        payrollDetails.remove(detail);
        detail.setPayroll(null);
    }
}
