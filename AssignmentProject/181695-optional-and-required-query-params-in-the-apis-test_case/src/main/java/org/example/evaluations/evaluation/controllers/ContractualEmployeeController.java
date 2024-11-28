package org.example.evaluations.evaluation.controllers;

import org.example.evaluations.evaluation.models.Permission;
import org.example.evaluations.evaluation.services.IEmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/contractualEmployee")
public class ContractualEmployeeController {

    @Autowired
    private IEmployeeService employeeService;

    //localhost:8080/contractualEmployee/isOnboarded/email/anurag?empId=1 - true
    //localhost:8080/contractualEmployee/isOnboarded/email/anurag - false
    @GetMapping("/isOnboarded/email/{email}")
    public Boolean isOnboarded(@PathVariable String email, @RequestParam(value = "empId",required = false) Long employeeId) {
        return employeeService.isOnboarded(employeeId,email);
    }

    //localhost:8080/contractualEmployee/permissions?roles=INSTRUCTOR&roles=TA
    //localhost:8080/contractualEmployee/permissions?roles=INSTRUCTOR,TA
    @GetMapping("/permissions")
    public List<Permission> getPermissionsBasedOnRoles(@RequestParam(required = true) List<String> roles) {
        System.out.println(roles);
       return employeeService.getPermissionsBasedOnRoles(roles);
    }

    //localhost:8080/contractualEmployee?email=abc&password=abc - true
    //localhost:8080/contractualEmployee?password=abc - false
    @GetMapping
    public Boolean isIdentityProvided(@RequestParam(required = false) String email, @RequestParam(required = false) String name, @RequestParam(required = false) String password) {
      return employeeService.isIdentityProvided(email, password, name);
    }
}