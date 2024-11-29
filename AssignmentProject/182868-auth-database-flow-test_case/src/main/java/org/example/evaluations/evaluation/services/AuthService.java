package org.example.evaluations.evaluation.services;

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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Optional;
import java.util.Random;

@Service
public class AuthService implements IAuthService {
    @Autowired
    private UserRepo userRepo;

    @Autowired
    private AuthCredentialRepo authCredentialRepo;

    @Autowired
    private SessionRepo sessionRepo;

    @Override
    public SignupResponse signup(SignupRequest request) {
        User user = new User();
        user.setAddress(request.getAddress());
        user.setFirstName(request.getFirstName());
        user.setLastName(request.getLastName());
        user.setPhoneNumber(request.getPhoneNumber());

        AuthCredential authCredential = new AuthCredential();
        authCredential.setEmail(request.getEmail());
        authCredential.setPassword(request.getPassword());
        authCredentialRepo.save(authCredential);
        user.setAuthCredential(authCredential);
        userRepo.save(user);

        SignupResponse signupResponse = new SignupResponse();
        signupResponse.setEmail(request.getEmail());
        signupResponse.setFirstName(request.getFirstName());
        signupResponse.setLastName(request.getLastName());
        return signupResponse;
    }

    @Override
    public LoginResponse login(LoginRequest loginRequest) throws UserNotSignedUpException, BadCredentialsException {
            Optional<AuthCredential> optionalAuthCredential = authCredentialRepo.findAuthCredentialByEmail(loginRequest.getEmail());
            if (optionalAuthCredential.isEmpty()) {
                throw new UserNotSignedUpException("Please sign up first !!");
            }

            String password = optionalAuthCredential.get().getPassword();
            if (!loginRequest.getPassword().equals(password)) {
                throw new BadCredentialsException("Please provide correct password !!");
            }

            Optional<User> userOptional = userRepo.findUserByAuthCredential(optionalAuthCredential.get());
            Optional<Session> optionalSession = sessionRepo.findSessionByUser(userOptional.get());
            if(optionalSession.isPresent()) {
                Session session = optionalSession.get();
                if(session.getSessionState().equals(SessionState.ACTIVE)) {
                    sessionRepo.deleteById(session.getId());
                }
            }

            Session session = new Session();
            session.setSessionState(SessionState.ACTIVE);
            session.setTtl(new Date(System.currentTimeMillis()+172800000));
            session.setToken(getSaltString());
            session.setUser(userOptional.get());
            sessionRepo.save(session);

            LoginResponse loginResponse = new LoginResponse();
            loginResponse.setToken(session.getToken());
            loginResponse.setTokenValidity(session.getTtl());
            loginResponse.setUserEmail(loginRequest.getEmail());
            return loginResponse;
    }

    private String getSaltString() {
        String SALTCHARS = "ABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890";
        StringBuilder salt = new StringBuilder();
        Random rnd = new Random();
        while (salt.length() < 15) {
            int index = (int) (rnd.nextFloat() * SALTCHARS.length());
            salt.append(SALTCHARS.charAt(index));
        }
        String saltStr = salt.toString();
        return saltStr;
    }
}
