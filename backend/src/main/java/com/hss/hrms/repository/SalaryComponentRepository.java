package com.hss.hrms.repository;

import com.hss.hrms.enums.ComponentType;
import com.hss.hrms.model.SalaryComponent;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.List;

@Repository
public interface SalaryComponentRepository extends JpaRepository<SalaryComponent, Integer> {
    Optional<SalaryComponent> findByComponentName(String componentName);
    List<SalaryComponent> findByComponentType(ComponentType componentType);
    List<SalaryComponent> findByIsActive(Boolean isActive);
    List<SalaryComponent> findByIsTaxable(Boolean isTaxable);
}