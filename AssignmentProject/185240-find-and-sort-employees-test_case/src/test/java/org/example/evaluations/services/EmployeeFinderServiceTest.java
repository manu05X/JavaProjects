package org.example.evaluations.services;

import org.example.evaluations.evaluation.dtos.SortParam;
import org.example.evaluations.evaluation.dtos.SortType;
import org.example.evaluations.evaluation.models.Employee;
import org.example.evaluations.evaluation.repos.EmployeeRepository;
import org.example.evaluations.evaluation.services.EmployeeFinderService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class EmployeeFinderServiceTest {

    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private EmployeeFinderService employeeFinderService;

    @Test
    public void testSort() {
        // Arrange
        String department = "Engineering";
        Integer pageNumber = 0;
        Integer pageSize = 5;

        SortParam sortParam1 = new SortParam();
        sortParam1.setParamName("performanceRating");
        sortParam1.setSortType(SortType.DESC);
        SortParam sortParam2 = new SortParam();
        sortParam2.setParamName("costToCompany");
        sortParam2.setSortType(SortType.ASC);
        List<SortParam> sortParams = new ArrayList<>();
        sortParams.add(sortParam1);
        sortParams.add(sortParam2);

        Employee employee2 = createEmployee("Johnny Doe",2000000D,4.1D);
        Employee employee1 = createEmployee("Alice Smith",1850000D,4.59D);
        employeeRepository.save(employee1);
        employeeRepository.save(employee2);


        // Act
        Page<Employee> result = employeeFinderService.findEmployees(department, pageNumber, pageSize, sortParams);

        // Assert
        assertEquals(2, result.getContent().size());
        assertEquals("Alice Smith", result.getContent().get(0).getFullName());
    }


    private Employee createEmployee(String fullName,Double ctc,Double rating) {
        Employee employee = new Employee();
        employee.setId(UUID.randomUUID());
        employee.setFullName(fullName);
        employee.setDepartment("Engineering");
        employee.setCostToCompany(ctc);
        employee.setPerformanceRating(rating);
        return employee;
    }
}
