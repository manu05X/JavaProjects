package org.example.evaluations.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.example.evaluations.evaluation.controllers.ContractualEmployeeController;
import org.example.evaluations.evaluation.models.Access;
import org.example.evaluations.evaluation.models.Permission;
import org.example.evaluations.evaluation.models.Resource;
import org.example.evaluations.evaluation.services.IEmployeeService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(ContractualEmployeeController.class)
public class ContractualEmployeeControllerMvcTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private IEmployeeService employeeService;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    public void testIsOnboardedWithBothEmailAndEmployeeIdProvided() throws Exception {
        String email = "test@example.com";
        Long employeeId = 123L;
        when(employeeService.isOnboarded(employeeId,email)).thenReturn(true);

        mockMvc.perform(get("/contractualEmployee/isOnboarded/email/{email}", email)
                        .param("empId", employeeId.toString()))
                .andExpect(status().isOk())
                .andExpect(content().string("true"));

        verify(employeeService, times(1)).isOnboarded(employeeId, email);
    }

    @Test
    public void testIsOnboardedWithEmployeeIdMissing() throws Exception {
        String email = "test@example.com";
        when(employeeService.isOnboarded(null,email)).thenReturn(false);

        mockMvc.perform(get("/contractualEmployee/isOnboarded/email/{email}", email))
                .andExpect(status().isOk())
                .andExpect(content().string("false"));

        verify(employeeService, times(1)).isOnboarded(null,email);
    }

    @Test
    public void testGetPermissionsBasedOnRolesWithNoRoleProvided() throws Exception {
        List<String> roles = List.of();
        List<Permission> permissions = Collections.emptyList();
        when(employeeService.getPermissionsBasedOnRoles(roles)).thenReturn(permissions);

        mockMvc.perform(get("/contractualEmployee/permissions")
                        .param("roles", String.join(",", roles)))
                .andExpect(status().isOk())
                .andExpect(content().json("[]"));

        verify(employeeService, times(1)).getPermissionsBasedOnRoles(roles);
    }

    @Test
    public void testGetPermissionsBasedOnRolesWithRolesMissing() throws Exception {
        List<String> roles = List.of();
        List<Permission> permissions = Collections.emptyList();
        when(employeeService.getPermissionsBasedOnRoles(roles)).thenReturn(permissions);

        mockMvc.perform(get("/contractualEmployee/permissions"))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void testGetPermissionsBasedOnRolesWithInstructorRoleProvided() throws Exception {
        List<String> roles = List.of("INSTRUCTOR");
        Permission permission = new Permission();
        permission.setAccess(Access.HOST);
        permission.setResource(Resource.LECTURE);
        List<Permission> permissions = new ArrayList<>();
        permissions.add(permission);
        when(employeeService.getPermissionsBasedOnRoles(roles)).thenReturn(permissions);

        mockMvc.perform(get("/contractualEmployee/permissions")
                        .param("roles", String.join(",", roles)))
                .andExpect(status().isOk())
                .andExpect(content().string(objectMapper.writeValueAsString(permissions)));

        verify(employeeService, times(1)).getPermissionsBasedOnRoles(roles);
    }

    @Test
    public void testGetPermissionsBasedOnRolesWithTaRoleProvided() throws Exception {
        List<String> roles = List.of("TA");
        Permission permission = new Permission();
        permission.setAccess(Access.HOST);
        permission.setResource(Resource.HELP_REQUEST);
        List<Permission> permissions = new ArrayList<>();
        permissions.add(permission);
        when(employeeService.getPermissionsBasedOnRoles(roles)).thenReturn(permissions);

        mockMvc.perform(get("/contractualEmployee/permissions")
                        .param("roles", String.join(",", roles)))
                .andExpect(status().isOk())
                .andExpect(content().string(objectMapper.writeValueAsString(permissions)));

        verify(employeeService, times(1)).getPermissionsBasedOnRoles(roles);
    }

    @Test
    public void testGetPermissionsBasedOnRolesWithTaAndInstructorRoleProvided() throws Exception {
        List<String> roles = new ArrayList<>();
        roles.add("TA");
        roles.add("INSTRUCTOR");

        Permission permission1 = new Permission();
        permission1.setAccess(Access.HOST);
        permission1.setResource(Resource.HELP_REQUEST);

        Permission permission2 = new Permission();
        permission2.setAccess(Access.HOST);
        permission2.setResource(Resource.LECTURE);

        List<Permission> permissions = new ArrayList<>();
        permissions.add(permission1);
        permissions.add(permission2);

        when(employeeService.getPermissionsBasedOnRoles(roles)).thenReturn(permissions);

        mockMvc.perform(get("/contractualEmployee/permissions")
                        .param("roles", String.join(",", roles)))
                .andExpect(status().isOk())
                .andExpect(content().string(objectMapper.writeValueAsString(permissions)));

        verify(employeeService, times(1)).getPermissionsBasedOnRoles(roles);
    }

    @Test
    public void testIsIdentityProvidedWithAllRequestParametersNonNull() throws Exception {
        String email = "test@example.com";
        String name = "John Doe";
        String password = "password";
        when(employeeService.isIdentityProvided(email, password, name)).thenReturn(true);

        mockMvc.perform(get("/contractualEmployee")
                        .param("email", email)
                        .param("name", name)
                        .param("password", password))
                .andExpect(status().isOk())
                .andExpect(content().string("true"));

        verify(employeeService, times(1)).isIdentityProvided(email, password, name);
    }

    @Test
    public void testIsIdentityProvidedWithNameMissing() throws Exception {
        String email = "test@example.com";
        String password = "password";
        when(employeeService.isIdentityProvided(email, password, null)).thenReturn(true);

        mockMvc.perform(get("/contractualEmployee")
                        .param("email", email)
                        .param("password", password))
                .andExpect(status().isOk())
                .andExpect(content().string("true"));

        verify(employeeService, times(1)).isIdentityProvided(email, password, null);
    }

    @Test
    public void testIsIdentityProvidedWithPasswordMissing() throws Exception {
        String email = "test@example.com";
        String name = "test";
        when(employeeService.isIdentityProvided(email, null, name)).thenReturn(false);

        mockMvc.perform(get("/contractualEmployee")
                        .param("email", email)
                        .param("name", name))
                .andExpect(status().isOk())
                .andExpect(content().string("false"));

        verify(employeeService, times(1)).isIdentityProvided(email, null, name);
    }

    @Test
    public void testIsIdentityProvidedWithAllRequestParametersMissing() throws Exception {
        when(employeeService.isIdentityProvided(null, null, null)).thenReturn(false);

        mockMvc.perform(get("/contractualEmployee"))
                .andExpect(status().isOk())
                .andExpect(content().string("false"));

        verify(employeeService, times(1)).isIdentityProvided(null, null, null);
    }

    @Test
    public void testIsIdentityProvidedWithEmailMissing() throws Exception {
        String name = "test";
        String password = "password";
        when(employeeService.isIdentityProvided(null, password, name)).thenReturn(true);

        mockMvc.perform(get("/contractualEmployee")
                        .param("name", name)
                        .param("password", password))
                .andExpect(status().isOk())
                .andExpect(content().string("true"));

        verify(employeeService, times(1)).isIdentityProvided(null, password, name);
    }
}
