package org.example.evaluations.evaluation.controllers;

import org.example.evaluations.evaluation.dtos.EmployeeFinderRequestDto;
import org.example.evaluations.evaluation.models.Employee;
import org.example.evaluations.evaluation.services.EmployeeFinderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;


@RestController
public class EmployeeFinderController {

    @Autowired
    private EmployeeFinderService employeeFinderService;

    @PostMapping("/employeeFinder")
    public Page<Employee> findEmployees(@RequestBody EmployeeFinderRequestDto employeeFinderRequestDto) {
        return employeeFinderService.findEmployees(employeeFinderRequestDto.getDepartment(), employeeFinderRequestDto.getPageNumber(), employeeFinderRequestDto.getPageSize(), employeeFinderRequestDto.getSortParamList());
    }
}
