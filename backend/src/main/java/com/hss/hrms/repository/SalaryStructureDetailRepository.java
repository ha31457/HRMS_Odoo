package com.hss.hrms.repository;

import com.hss.hrms.model.SalaryStructureDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SalaryStructureDetailRepository extends JpaRepository<SalaryStructureDetail, Integer> {
    List<SalaryStructureDetail> findBySalaryStructureId(Integer salaryStructureId);
    List<SalaryStructureDetail> findByComponentId(Integer componentId);
}