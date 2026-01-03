package com.hss.hrms.model;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;

@Entity
@Table(name = "payroll_details", indexes = {
        @Index(name = "idx_payroll", columnList = "payroll_id")
})
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PayrollDetail {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "payroll_detail_id")
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "payroll_id", nullable = false)
    private Payroll payroll;

    @ManyToOne
    @JoinColumn(name = "component_id", nullable = false)
    private SalaryComponent component;

    @Column(nullable = false, precision = 10, scale = 2)
    private BigDecimal amount;
}

