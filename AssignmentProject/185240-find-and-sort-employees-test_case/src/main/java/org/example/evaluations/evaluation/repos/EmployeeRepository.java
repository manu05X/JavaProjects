package org.example.evaluations.evaluation.repos;

import org.example.evaluations.evaluation.models.Employee;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee,Long> {

    Page<Employee> findEmployeeByDepartment(String department, Pageable pageable);
}
