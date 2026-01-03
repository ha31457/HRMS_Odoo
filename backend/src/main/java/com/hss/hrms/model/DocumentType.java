package com.hss.hrms.model;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "document_types")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class DocumentType {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "document_type_id")
    private Integer id;

    @Column(name = "type_name", unique = true, nullable = false, length = 100)
    private String typeName;

    @Column(columnDefinition = "TEXT")
    private String description;

    @Column(name = "is_mandatory")
    private Boolean isMandatory = false;

    @Column(name = "created_at", updatable = false)
    private LocalDateTime createdAt;

    @PrePersist
    protected void onCreate() {
        createdAt = LocalDateTime.now();
    }
}
