package com.hss.hrms.model;
import com.hss.hrms.enums.LeaveCategory;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "leave_types", indexes = {
        @Index(name = "idx_lt_category", columnList = "leave_category")
})
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class LeaveType {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "leave_type_id")
    private Integer id;

    @Column(name = "leave_type_name", unique = true, nullable = false, length = 100)
    private String leaveTypeName;

    @Enumerated(EnumType.STRING)
    @Column(name = "leave_category", nullable = false)
    private LeaveCategory leaveCategory;

    @Column(name = "max_days_per_year")
    private Integer maxDaysPerYear;

    @Column(name = "carry_forward_allowed")
    private Boolean carryForwardAllowed = false;

    @Column(columnDefinition = "TEXT")
    private String description;

    @Column(name = "is_active")
    private Boolean isActive = true;

    @Column(name = "created_at", updatable = false)
    private LocalDateTime createdAt;

    @PrePersist
    protected void onCreate() {
        createdAt = LocalDateTime.now();
    }
}
