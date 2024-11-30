package org.example.evaluations.services;

import io.jsonwebtoken.*;
import io.jsonwebtoken.security.MacAlgorithm;
import io.jsonwebtoken.security.SecretKeyBuilder;
import org.example.evaluations.evaluation.models.User;
import org.example.evaluations.evaluation.repos.UserRepo;
import org.example.evaluations.evaluation.services.AuthenticationService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import javax.crypto.SecretKey;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@SpringBootTest
public class AuthenticationServiceTest {

    @InjectMocks
    private AuthenticationService authenticationService;

    @Mock
    private RedisTemplate<String,Object> redisTemplate;

   @Mock
    private RedisConnectionFactory redisConnectionFactory;

   @Mock
    private RedisConnection redisConnectionMock;

   @Mock
   private ValueOperations valueOperations;

   @Autowired
    private SecretKey secretKey;

    @Mock
    private MacAlgorithm macAlgorithm;

    @Mock
    private UserRepo userRepo;

    @Mock
    private SecretKeyBuilder secretKeyBuilder;

    @Mock
    private JwtParserBuilder jwtParserBuilder;

    @Mock
    private JwtParser jwtParser;



    @BeforeEach
    public void setUp() {
        redisTemplate.setConnectionFactory(redisConnectionFactory);
        when(redisConnectionFactory.getConnection()).thenReturn(redisConnectionMock);
        redisTemplate.afterPropertiesSet();

        MacAlgorithm algorithm = Jwts.SIG.HS256;
        secretKey = algorithm.key().build();
        authenticationService.setSecretKey(secretKey);
        when(macAlgorithm.key()).thenReturn(secretKeyBuilder);
        when(secretKeyBuilder.build()).thenReturn(secretKey);

        when(jwtParserBuilder.verifyWith(secretKey)).thenReturn(jwtParserBuilder);
        when(jwtParserBuilder.build()).thenReturn(jwtParser);
    }

    @Test
    public void testLogin_Success() {
        String email = "test@example.com";
        String password = "password123";
        User user = new User();
        user.setEmail(email);
        user.setPassword(password);

        when(redisTemplate.opsForValue()).thenReturn(valueOperations);
        when(userRepo.findUserByEmail(email)).thenReturn(Optional.of(user));

        ResponseEntity<Boolean> response = authenticationService.login(email, password);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertTrue(response.getBody());
        verify(redisTemplate.opsForValue(),times(1)).set(eq(email),any(String.class));
    }

    @Test
    public void testLogin_Failure_UserNotFound() {
        String email = "nonexistent@example.com";
        String password = "password123";

        when(redisTemplate.opsForValue()).thenReturn(valueOperations);
        when(userRepo.findUserByEmail(email)).thenReturn(Optional.empty());

        ResponseEntity<Boolean> response = authenticationService.login(email, password);

        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertFalse(response.getBody());
    }

    @Test
    public void testLogin_Failure_WrongPassword() {
        String email = "test@example.com";
        String wrongPassword = "wrongPassword";
        User user = new User();
        user.setEmail(email);
        user.setPassword("password123");

        when(redisTemplate.opsForValue()).thenReturn(valueOperations);
        when(userRepo.findUserByEmail(email)).thenReturn(Optional.of(user));

        ResponseEntity<Boolean> response = authenticationService.login(email, wrongPassword);

        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertFalse(response.getBody());
    }

    @Test
    public void testValidateToken_TokenNotFound() {
        String email = "test@example.com";
        String token = "tokenThatDoesNotExist";

        when(redisTemplate.opsForValue()).thenReturn(valueOperations);
        when(valueOperations.get(email)).thenReturn(null);

        Exception exception = assertThrows(RuntimeException.class, () -> {
            authenticationService.validateToken(email, token);
        });

        assertEquals("Authentication Error !!", exception.getMessage());
    }
}

