package org.example.evaluations.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.example.evaluations.evaluation.controllers.UserController;
import org.example.evaluations.evaluation.dtos.UserRequestDto;
import org.example.evaluations.evaluation.models.Address;
import org.example.evaluations.evaluation.models.Name;
import org.example.evaluations.evaluation.models.User;
import org.example.evaluations.evaluation.services.IUserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(UserController.class)
public class UserControllerMvcTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private IUserService userService;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void testAddUser() throws Exception {
        UserRequestDto userRequestDto = new UserRequestDto();
        userRequestDto.setUsername("john_doe");
        userRequestDto.setPassword("password123");
        userRequestDto.setEmail("john@example.com");
        Name name = new Name();
        name.setFirstname("John");
        name.setLastname("Doe");
        userRequestDto.setName(name);
        Address address = new Address();
        address.setCity("Springfield");
        address.setStreet("Main St");
        address.setNumber(123L);
        userRequestDto.setAddress(address);

        User user = new User();
        user.setUsername("john_doe");
        user.setPassword("password123");
        user.setEmail("john@example.com");
        user.setName(name);
        user.setAddress(address);

        when(userService.add(any(User.class))).thenReturn(user);

        mockMvc.perform(post("/user")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(userRequestDto)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.username").value("john_doe"))
                .andExpect(jsonPath("$.email").value("john@example.com"))
                .andExpect(jsonPath("$.name.firstname").value("John"))
                .andExpect(jsonPath("$.address.city").value("Springfield"));
    }

    @Test
    void testUpdateUser() throws Exception {
        UserRequestDto userRequestDto = new UserRequestDto();
        userRequestDto.setUsername("john_doe_updated");
        userRequestDto.setPassword("newpassword123");
        userRequestDto.setEmail("john_updated@example.com");
        Name name = new Name();
        name.setFirstname("Johny");
        name.setLastname("Does");
        userRequestDto.setName(name);
        Address address = new Address();
        address.setCity("Shelbyville");
        address.setStreet("Elm St");
        address.setNumber(456L);
        userRequestDto.setAddress(address);

        User user = new User();
        user.setUsername("john_doe_updated");
        user.setPassword("newpassword123");
        user.setEmail("john_updated@example.com");
        user.setName(name);
        user.setAddress(address);

        when(userService.update(any(User.class), anyLong())).thenReturn(user);

        mockMvc.perform(put("/user/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(userRequestDto)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.username").value("john_doe_updated"))
                .andExpect(jsonPath("$.email").value("john_updated@example.com"))
                .andExpect(jsonPath("$.address.city").value("Shelbyville"));
    }
}
