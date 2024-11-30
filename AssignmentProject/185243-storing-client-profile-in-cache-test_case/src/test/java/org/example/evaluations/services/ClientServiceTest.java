package org.example.evaluations.services;

import org.example.evaluations.evaluation.exceptions.UserNotFoundException;
import org.example.evaluations.evaluation.models.Client;
import org.example.evaluations.evaluation.repos.ClientRepo;
import org.example.evaluations.evaluation.services.ClientService;
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
public class ClientServiceTest {

    @InjectMocks
    private ClientService clientService;

    @Mock
    private ClientRepo clientRepo;

    @Mock
    private RedisTemplate<String,Object> redisTemplate;

   @Mock
    private RedisConnectionFactory redisConnectionFactory;

   @Mock
    private RedisConnection redisConnectionMock;

   @Mock
   private HashOperations hashOperations;

    private Client client;

    @BeforeEach
    public void setUp() {
        redisTemplate.setConnectionFactory(redisConnectionFactory);
        when(redisConnectionFactory.getConnection()).thenReturn(redisConnectionMock);
        redisTemplate.afterPropertiesSet();

        client = new Client();
        client.setId(1L);
        client.setName("John Doe");
        client.setEmail("john.doe@example.com");
    }

    @Test
    public void testGetClientFromId_Success() throws UserNotFoundException {
        when(clientRepo.findById(1L)).thenReturn(Optional.of(client));

        Client foundClient = clientService.getClientFromId(1L);

        assertNotNull(foundClient);
        assertEquals("John Doe", foundClient.getName());
        verify(clientRepo, times(1)).findById(1L);
    }

    @Test
    public void testGetClientFromId_UserNotFoundException() {
        when(clientRepo.findById(1L)).thenReturn(Optional.empty());

        Exception exception = assertThrows(UserNotFoundException.class, () -> {
            clientService.getClientFromId(1L);
        });

        assertEquals("Please signup first", exception.getMessage());
        verify(clientRepo, times(1)).findById(1L);
    }

    @Test
    public void testGetClientFromEmail_Success() throws UserNotFoundException {
        when(redisTemplate.opsForHash()).thenReturn(hashOperations);
        when(hashOperations.get("CLIENTS", "john.doe@example.com")).thenReturn(client);

        Client foundClient = clientService.getClientFromEmail("john.doe@example.com");

        assertNotNull(foundClient);
        assertEquals("John Doe", foundClient.getName());
        verify(redisTemplate.opsForHash(), times(1)).get("CLIENTS", "john.doe@example.com");
    }

    @Test
    public void testGetClientFromEmail_UserNotFoundException() {
        when(redisTemplate.opsForHash()).thenReturn(hashOperations);
        when(hashOperations.get("CLIENTS", "john.doe@example.com")).thenReturn(null);

        Exception exception = assertThrows(UserNotFoundException.class, () -> {
            clientService.getClientFromEmail("john.doe@example.com");
        });

        assertEquals("Please signup first", exception.getMessage());
        verify(redisTemplate.opsForHash(), times(1)).get("CLIENTS", "john.doe@example.com");
    }
}
