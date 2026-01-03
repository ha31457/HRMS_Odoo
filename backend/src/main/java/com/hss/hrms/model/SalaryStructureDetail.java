package com.hss.hrms.model;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;

@Entity
@Table(name = "salary_structure_details", indexes = {
        @Index(name = "idx_salary_structure", columnList = "salary_structure_id"),
        @Index(name = "idx_salary_component", columnList = "component_id")
})
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SalaryStructureDetail {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "detail_id")
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "salary_structure_id", nullable = false)
    private EmployeeSalaryStructure salaryStructure;

    @ManyToOne
    @JoinColumn(name = "component_id", nullable = false)
    private SalaryComponent component;

    @Column(nullable = false, precision = 10, scale = 2)
    private BigDecimal amount;

    @Column(precision = 5, scale = 2)
    private BigDecimal percentage;
}
