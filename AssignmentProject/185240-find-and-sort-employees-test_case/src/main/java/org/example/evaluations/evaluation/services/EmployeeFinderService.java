package org.example.evaluations.evaluation.services;

import org.example.evaluations.evaluation.dtos.SortParam;
import org.example.evaluations.evaluation.dtos.SortType;
import org.example.evaluations.evaluation.models.Employee;
import org.example.evaluations.evaluation.repos.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeFinderService {

    @Autowired
    private EmployeeRepository employeeRepository;

    public Page<Employee> findEmployees(String department, Integer pageNumber, Integer pageSize, List<SortParam> sortParams) {
        Sort sort = null;
        if(!sortParams.isEmpty()) {
            if(sortParams.get(0).getSortType().equals(SortType.ASC))
                sort = Sort.by(sortParams.get(0).getParamName());
            else
                sort = Sort.by(sortParams.get(0).getParamName()).descending();

            for(int i = 1; i< sortParams.size(); i++) {
                if(sortParams.get(i).getSortType().equals(SortType.ASC))
                    sort.and(Sort.by(sortParams.get(i).getParamName()));
                else
                    sort.and(Sort.by(sortParams.get(i).getParamName()).descending());
            }
        }

        return employeeRepository.findEmployeeByDepartment(department, PageRequest.of(pageNumber,pageSize,sort));
    }
}
