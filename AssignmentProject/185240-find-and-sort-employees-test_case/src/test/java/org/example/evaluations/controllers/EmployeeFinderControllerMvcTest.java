package org.example.evaluations.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.example.evaluations.evaluation.controllers.EmployeeFinderController;
import org.example.evaluations.evaluation.dtos.EmployeeFinderRequestDto;
import org.example.evaluations.evaluation.models.Employee;
import org.example.evaluations.evaluation.services.EmployeeFinderService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.PageImpl;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(EmployeeFinderController.class)
public class EmployeeFinderControllerMvcTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private EmployeeFinderService employeeFinderService;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    public void testFindEmployees() throws Exception {
        // Arrange
        EmployeeFinderRequestDto requestDto = new EmployeeFinderRequestDto();
        requestDto.setDepartment("Engineering");
        requestDto.setPageNumber(0);
        requestDto.setPageSize(5);

        Employee employee1 = createEmployee();
        Employee employee2 = createEmployee();
        List<Employee> employees = Arrays.asList(employee1, employee2);
        Page<Employee> employeePage = new PageImpl<>(employees, PageRequest.of(0, 5), employees.size());

        when(employeeFinderService.findEmployees("Engineering", 0, 5, requestDto.getSortParamList())).thenReturn(employeePage);

        // Act & Assert
        mockMvc.perform(post("/employeeFinder")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(requestDto)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.content[0].id").value(employee1.getId().toString()))
                .andExpect(jsonPath("$.content[1].id").value(employee2.getId().toString()));
    }

    private Employee createEmployee() {
        Employee employee = new Employee();
        employee.setId(UUID.randomUUID());
        employee.setAlias("john_doe");
        employee.setFullName("John Doe");
        employee.setPhoneNumber("1234567890");
        employee.setCostToCompany(50000.0);
        employee.setJoiningDate(new Date());
        employee.setPerformanceRating(4.5);
        employee.setDepartment("Engineering");
        return employee;
    }
}
