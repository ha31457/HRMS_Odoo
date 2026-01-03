package com.hss.hrms.repository;

import com.hss.hrms.model.AuditLog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface AuditLogRepository extends JpaRepository<AuditLog, Integer> {
    List<AuditLog> findByUserId(Integer userId);
    List<AuditLog> findByTableName(String tableName);
    List<AuditLog> findByTableNameAndRecordId(String tableName, Integer recordId);
    List<AuditLog> findByActionType(String actionType);
    List<AuditLog> findByCreatedAtBetween(LocalDateTime startDate, LocalDateTime endDate);
}