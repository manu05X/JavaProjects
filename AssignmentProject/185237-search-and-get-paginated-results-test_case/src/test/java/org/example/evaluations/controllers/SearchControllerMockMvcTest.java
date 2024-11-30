package org.example.evaluations.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.example.evaluations.evaluation.controllers.UserSearchController;
import org.example.evaluations.evaluation.dtos.UserSearchRequestDto;
import org.example.evaluations.evaluation.models.Sex;
import org.example.evaluations.evaluation.models.User;
import org.example.evaluations.evaluation.services.UserSearchService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(UserSearchController.class)
public class SearchControllerMockMvcTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UserSearchService userSearchService;

    @Autowired
    private ObjectMapper objectMapper;


    @Test
    public void testSearchUsersByAddress() throws Exception {
        // Arrange
        User user1 = createUser(1L, "user1@example.com", "User One", "1234567890", "Street", Sex.MALE,5);
        User user2 = createUser(2L, "user2@example.com", "User Two", "0987654321", "Street", Sex.FEMALE,21);

        List<User> users = Arrays.asList(user1, user2);

        UserSearchRequestDto requestDto = new UserSearchRequestDto();
        requestDto.setQuery("Street");
        requestDto.setPageNumber(0);

        when(userSearchService.getUsersHavingAddress(any(String.class), any(Integer.class))).thenReturn(users);

        // Act & Assert
        mockMvc.perform(post("/search")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(requestDto)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id").value(1))
                .andExpect(jsonPath("$[0].emailId").value("user1@example.com"))
                .andExpect(jsonPath("$[1].id").value(2))
                .andExpect(jsonPath("$[1].emailId").value("user2@example.com"));
    }

    @Test
    public void testSearchLadies() throws Exception {
        // Arrange
        User user1 = createUser(1L, "lady1@example.com", "Lady One", "1234567890", "123 Street", Sex.FEMALE,12);
        List<User> ladies = Arrays.asList(user1);

        when(userSearchService.getDetailsOfAllLadies(0)).thenReturn(ladies);

        // Act & Assert
        mockMvc.perform(get("/search/ladies").param("pageNumber","0"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id").value(1))
                .andExpect(jsonPath("$[0].emailId").value("lady1@example.com"));
    }

    @Test
    public void testSearchAdultMales() throws Exception {
        // Arrange
        User user1 = createUser(1L, "male1@example.com", "Male One", "1234567890", "123 Street", Sex.MALE,19);
        List<User> males = Arrays.asList(user1);

        when(userSearchService.getDetailsOfAllAdultMales(0)).thenReturn(males);

        // Act & Assert
        mockMvc.perform(get("/search/adultMales").param("pageNumber","0"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id").value(1))
                .andExpect(jsonPath("$[0].emailId").value("male1@example.com"));
    }

    private User createUser(Long id, String emailId, String name, String phoneNumber, String address, Sex sex, Integer age) {
        User user = new User();
        user.setId(id);
        user.setEmailId(emailId);
        user.setName(name);
        user.setPhoneNumber(phoneNumber);
        user.setAddress(address);
        user.setSex(sex);
        user.setAge(age);
        return user;
    }
}
