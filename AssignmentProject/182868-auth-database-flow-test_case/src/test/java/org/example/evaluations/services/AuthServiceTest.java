package org.example.evaluations.services;

import org.example.evaluations.evaluation.dtos.LoginRequest;
import org.example.evaluations.evaluation.dtos.LoginResponse;
import org.example.evaluations.evaluation.dtos.SignupRequest;
import org.example.evaluations.evaluation.dtos.SignupResponse;
import org.example.evaluations.evaluation.exceptions.BadCredentialsException;
import org.example.evaluations.evaluation.exceptions.UserNotSignedUpException;
import org.example.evaluations.evaluation.models.AuthCredential;
import org.example.evaluations.evaluation.models.Session;
import org.example.evaluations.evaluation.models.SessionState;
import org.example.evaluations.evaluation.models.User;
import org.example.evaluations.evaluation.repos.AuthCredentialRepo;
import org.example.evaluations.evaluation.repos.SessionRepo;
import org.example.evaluations.evaluation.repos.UserRepo;
import org.example.evaluations.evaluation.services.AuthService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@SpringBootTest
public class AuthServiceTest {

    @Autowired
    private AuthService authService;

    @MockBean
    private SessionRepo sessionRepo;

    @MockBean
    private AuthCredentialRepo authCredentialRepo;

    @MockBean
    private UserRepo userRepo;

    @Test
    public void testSignup() {
        SignupRequest request = new SignupRequest();
        request.setAddress("Address");
        request.setFirstName("John");
        request.setLastName("Doe");
        request.setPhoneNumber("1234567890");
        request.setEmail("john.doe@example.com");
        request.setPassword("password");

        AuthCredential authCredential = new AuthCredential();
        authCredential.setEmail(request.getEmail());
        authCredential.setPassword(request.getPassword());

        User user = new User();
        user.setAddress(request.getAddress());
        user.setFirstName(request.getFirstName());
        user.setLastName(request.getLastName());
        user.setPhoneNumber(request.getPhoneNumber());
        user.setAuthCredential(authCredential);

        when(authCredentialRepo.save(any(AuthCredential.class))).thenReturn(authCredential);
        when(userRepo.save(any(User.class))).thenReturn(user);

        SignupResponse response = authService.signup(request);

        assertEquals(request.getEmail(), response.getEmail());
        assertEquals(request.getFirstName(), response.getFirstName());
        assertEquals(request.getLastName(), response.getLastName());

        verify(authCredentialRepo).save(any(AuthCredential.class));
        verify(userRepo).save(any(User.class));
    }

    @Test
    public void testLogin_Success() throws UserNotSignedUpException, BadCredentialsException {
        LoginRequest request = new LoginRequest();
        request.setEmail("john.doe@example.com");
        request.setPassword("password");

        AuthCredential authCredential = new AuthCredential();
        authCredential.setEmail(request.getEmail());
        authCredential.setPassword(request.getPassword());

        User user = new User();
        user.setAuthCredential(authCredential);

        Session existingSession = new Session();
        existingSession.setSessionState(SessionState.ACTIVE);

        when(authCredentialRepo.findAuthCredentialByEmail(request.getEmail())).thenReturn(Optional.of(authCredential));
        when(userRepo.findUserByAuthCredential(authCredential)).thenReturn(Optional.of(user));
        when(sessionRepo.findSessionByUser(user)).thenReturn(Optional.of(existingSession));
        when(sessionRepo.save(any(Session.class))).thenAnswer(invocation -> invocation.getArgument(0));

        LoginResponse response = authService.login(request);

        assertNotNull(response.getToken());
        assertEquals(request.getEmail(), response.getUserEmail());

        verify(authCredentialRepo).findAuthCredentialByEmail(request.getEmail());
        verify(sessionRepo).deleteById(any());
        verify(sessionRepo).save(any(Session.class));
    }

    @Test
    public void testLogin_UserNotSignedUp() {
        LoginRequest request = new LoginRequest();
        request.setEmail("john.doe@example.com");
        request.setPassword("password");

        when(authCredentialRepo.findAuthCredentialByEmail(request.getEmail())).thenReturn(Optional.empty());

        Exception exception = assertThrows(UserNotSignedUpException.class, () -> authService.login(request));

        assertEquals("Please sign up first !!",exception.getMessage());

        verify(authCredentialRepo).findAuthCredentialByEmail(request.getEmail());
    }

    @Test
    public void testLogin_BadCredentials() {
        LoginRequest request = new LoginRequest();
        request.setEmail("john.doe@example.com");
        request.setPassword("wrongpassword");

        AuthCredential authCredential = new AuthCredential();
        authCredential.setEmail(request.getEmail());
        authCredential.setPassword("password");

        when(authCredentialRepo.findAuthCredentialByEmail(request.getEmail())).thenReturn(Optional.of(authCredential));

        Exception exception = assertThrows(BadCredentialsException.class, () -> authService.login(request));

        assertEquals("Please provide correct password !!",exception.getMessage());

        verify(authCredentialRepo).findAuthCredentialByEmail(request.getEmail());
    }
}
