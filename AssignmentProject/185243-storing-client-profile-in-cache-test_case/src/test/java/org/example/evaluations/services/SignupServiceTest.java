package org.example.evaluations.services;

import org.example.evaluations.evaluation.exceptions.UserAlreadyExistsException;
import org.example.evaluations.evaluation.models.Client;
import org.example.evaluations.evaluation.repos.ClientRepo;
import org.example.evaluations.evaluation.services.SignupService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@SpringBootTest
public class SignupServiceTest {

    @InjectMocks
    private SignupService signupService;

    @Mock
    private RedisTemplate<String,Object> redisTemplate;

    @Mock
    private RedisConnectionFactory redisConnectionFactory;

    @Mock
    private RedisConnection redisConnectionMock;

    @Mock
    private HashOperations hashOperations;

    private Client client;

    @Mock
    private ClientRepo clientRepo;

    @BeforeEach
    public void setUp() {
        redisTemplate.setConnectionFactory(redisConnectionFactory);
        when(redisConnectionFactory.getConnection()).thenReturn(redisConnectionMock);
        redisTemplate.afterPropertiesSet();

        client = new Client();
        client.setId(1L);
        client.setName("John Doe");
        client.setEmail("john.doe@example.com");
        client.setPassword("password123");
        client.setPhoneNumber("1234567890");
    }

    @Test
    public void testSignup_Success() throws UserAlreadyExistsException {
        // Mock the behavior of clientRepo and redisTemplate
        when(redisTemplate.opsForHash()).thenReturn(hashOperations);
        when(clientRepo.findClientByEmail(client.getEmail())).thenReturn(Optional.empty());

        // Call the signup method
        Client createdClient = signupService.signup(client.getEmail(), client.getPassword(), client.getName(), client.getPhoneNumber());

        // Verify results
        assertNotNull(createdClient);
        assertEquals(client.getEmail(), createdClient.getEmail());
        assertEquals(client.getName(), createdClient.getName());
        assertNotNull(createdClient.getCreatedOn());
        assertNotNull(createdClient.getPassword());
        assertNotNull(createdClient.getPhoneNumber());

        // Verify interactions
        verify(redisTemplate.opsForHash(), times(1)).put("CLIENTS", client.getEmail(), createdClient);
        verify(clientRepo, times(1)).save(createdClient);
    }

    @Test
    public void testSignup_UserAlreadyExistsException() {
        when(redisTemplate.opsForHash()).thenReturn(hashOperations);

        // Mock the behavior of clientRepo to return an existing client
        when(clientRepo.findClientByEmail(client.getEmail())).thenReturn(Optional.of(client));

        // Call the signup method and expect an exception
        Exception exception = assertThrows(UserAlreadyExistsException.class, () -> {
            signupService.signup(client.getEmail(), client.getPassword(), client.getName(), client.getPhoneNumber());
        });

        // Verify exception message
        assertEquals("Please try out with some other email.", exception.getMessage());

        // Verify that save and redis put were not called
        verify(clientRepo, never()).save(any());
        verify(redisTemplate.opsForHash(), never()).put(anyString(), any(), any());
    }
}
