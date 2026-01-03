package com.hss.hrms.repository;

import com.hss.hrms.enums.AddressType;
import com.hss.hrms.model.EmployeeAddress;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface EmployeeAddressRepository extends JpaRepository<EmployeeAddress, Integer> {
    List<EmployeeAddress> findByEmployeeId(Integer employeeId);
    Optional<EmployeeAddress> findByEmployeeIdAndAddressType(Integer employeeId, AddressType addressType);
    Optional<EmployeeAddress> findByEmployeeIdAndIsPrimaryTrue(Integer employeeId);
}