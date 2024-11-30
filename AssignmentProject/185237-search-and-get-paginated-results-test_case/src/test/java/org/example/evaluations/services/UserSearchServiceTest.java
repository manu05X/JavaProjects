package org.example.evaluations.services;

import org.example.evaluations.evaluation.models.Sex;
import org.example.evaluations.evaluation.models.User;
import org.example.evaluations.evaluation.repos.UserRepository;
import org.example.evaluations.evaluation.services.UserSearchService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class UserSearchServiceTest {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserSearchService userSearchService;


    @Test
    public void testGetUsersHavingAddress() {
        // Arrange
        User user1 = createUser(1L, "user1@example.com", "User One", "Street", Sex.MALE,3);
        User user2 = createUser(2L, "user2@example.com", "User Two", "Street", Sex.MALE,7);
        userRepository.save(user1);
        userRepository.save(user2);

        // Act
        List<User> result = userSearchService.getUsersHavingAddress("Street", 0);

        // Assert
        assertEquals(2, result.size(),"You need to declare `findUserByAddressEquals` in UserRepo");
        assertEquals("user1@example.com", result.get(0).getEmailId(),"You need to declare `findUserByAddressEquals` in UserRepo");
    }

    @Test
    public void testGetDetailsOfAllLadies() {
        // Arrange
        User lady1 = createUser(3L, "lady1@example.com", "Lady One", "123 Street", Sex.FEMALE,12);
        userRepository.save(lady1);

        // Act
        List<User> result = userSearchService.getDetailsOfAllLadies(0);

        // Assert
        assertEquals(1, result.size(), "You need to declare `findUserBySexEquals` in UserRepo");
        assertEquals("lady1@example.com", result.get(0).getEmailId(),"You need to declare `findUserBySexEquals` in UserRepo");
    }

    @Test
    public void testGetDetailsOfAllAdultMales() {
        // Arrange
        User male1 = createUser(5L, "male1@example.com", "Male One", "ABC Nagar", Sex.MALE,21);
        userRepository.save(male1);

        // Act
        List<User> result = userSearchService.getDetailsOfAllAdultMales(0);

        // Assert
        assertEquals(1, result.size(),"You need to declare `findUserBySexAndAgeGreaterThanEqual` in UserRepo");
        assertEquals("male1@example.com", result.get(0).getEmailId(),"You need to declare `findUserBySexAndAgeGreaterThanEqual` in UserRepo");
    }

    private User createUser(Long id, String emailId, String name, String address, Sex sex,Integer age) {
        User user = new User();
        user.setId(id);
        user.setEmailId(emailId);
        user.setName(name);
        user.setAddress(address);
        user.setSex(sex);
        user.setAge(age);
        return user;
    }
}
