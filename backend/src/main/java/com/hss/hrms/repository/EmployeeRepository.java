    package com.hss.hrms.repository;

    import com.hss.hrms.model.Employee;
    import org.springframework.data.jpa.repository.JpaRepository;
    import org.springframework.data.jpa.repository.Query;
    import org.springframework.stereotype.Repository;

    import java.util.Optional;
    import java.util.List;

    @Repository
    public interface EmployeeRepository extends JpaRepository<Employee, Integer> {
        Optional<Employee> findByEmployeeCode(String employeeCode);
        Optional<Employee> findByUserId(Integer userId);
        List<Employee> findByFirstNameContainingOrLastNameContaining(String firstName, String lastName);
        boolean existsByEmployeeCode(String employeeCode);

        @Query("SELECT e FROM Employee e WHERE CONCAT(e.firstName, ' ', e.lastName) LIKE %?1%")
        List<Employee> searchByFullName(String fullName);
    }