package com.hss.hrms.model;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "designations", indexes = {
        @Index(name = "idx_designation_level", columnList = "designation_level")
})
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Designation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "designation_id")
    private Integer id;

    @Column(name = "designation_name", unique = true, nullable = false, length = 100)
    private String designationName;

    @Column(name = "designation_level", nullable = false)
    private Integer designationLevel;

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
