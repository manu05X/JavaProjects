package com.example.userServiceAuth.services;

import com.example.userServiceAuth.dtos.UserDto;
import com.example.userServiceAuth.models.Session;
import com.example.userServiceAuth.models.SessionStatus;
import com.example.userServiceAuth.models.User;
import com.example.userServiceAuth.repositories.SessionRepository;
import com.example.userServiceAuth.repositories.UserRepository;
import io.jsonwebtoken.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.MultiValueMap;
import org.springframework.util.MultiValueMapAdapter;

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.time.LocalDate;
import java.util.*;

@Service
public class AuthService {
    private UserRepository userRepository;
    private SessionRepository sessionRepository;

    // Not to create a new instance each time it is used we will keep it in SpringSecurity and make its bean their,
    // so we do not need to create a instance each time only single instance of it will be present across the app.
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    private SecretKey secretKey;


    @Autowired
    public AuthService(UserRepository userRepository, SessionRepository sessionRepository, BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.userRepository = userRepository;
        this.sessionRepository = sessionRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;

        secretKey = Jwts.SIG.HS256.key().build();
    }

    public UserDto signUp(String email, String password) {
        User user = new User(); // Creating new user Obj

        //Setting the email and password in user obj
        user.setEmail(email);
        //user.setPassword(password);
        user.setPassword(bCryptPasswordEncoder.encode(password)); // encrypting it

        User savedUser = userRepository.save(user); // saving in user obj in DB
        return UserDto.from(savedUser);
    }

    // Note: This method should return a custom object containing token, headers, etc
    // For now, to avoid creating an object, directly returning ResponseEntity from here
    public ResponseEntity<UserDto> login(String email, String password) {
        Optional<User> userOptional = userRepository.findByEmail(email);

        //
        if (userOptional.isEmpty()) {
            return null; // Add custom exception
        }
        // User is present in DB

        User user = userOptional.get(); // now get the user as it exist in db

        //
        if(!bCryptPasswordEncoder.matches(password, user.getPassword())){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }



        //String token = RandomStringUtils.randomAlphanumeric(30); // generate a random string token using RandomStringUtils from lib apache.commons of dependencies commons-lang3

        //now we will use jwt to generate token
        Map<String, Object> jwtData = new HashMap<>();
        jwtData.put("email", email);
        jwtData.put("createAt", new Date());
        jwtData.put("expiryAt", new Date(LocalDate.now().plusDays(3).toEpochDay()));

        // Should not create secret key here
        //SecretKey secretKey = Jwts.SIG.HS256.key().build();

        String token = Jwts
                .builder()
                .claims(jwtData)
                .signWith(secretKey, SignatureAlgorithm.HS256)
                .compact();



        Session session = new Session(); // creating a session for the user
        session.setSessionStatus(SessionStatus.ACTIVE); // set it to ACTIVE
        session.setToken(token); // Setting the generated token to session
        session.setUser(user); // setting the user to session


        sessionRepository.save(session); // saving the session to session table db

        UserDto userDto = UserDto.from(user);

        MultiValueMap<String, String> headers = new MultiValueMapAdapter<>(new HashMap<>());
        // header expect a multiValueMap as for same session cookies it can have multiple value of token and session
//        Set-Cookie: auth-token=abcd1234
//        Set-Cookie: session-id=xyz789
        headers.add(HttpHeaders.SET_COOKIE, "auth-token:" + token);

        ResponseEntity<UserDto> response = new ResponseEntity<>(
                userDto,
                headers,
                HttpStatus.OK
        );

        return response;
    }


    public void logout(String token, Long userId) {
        Optional<Session> sessionOptional = sessionRepository.findByTokenAndUser_Id(token, userId);

        if (sessionOptional.isEmpty()) {
            return;
        }

        Session session = sessionOptional.get();
        session.setSessionStatus(SessionStatus.ENDED);

        sessionRepository.save(session);
    }



    public SessionStatus validate(String token, Long userId) {
        // Step 1: Query the database to find a session matching the provided token and userId
        Optional<Session> sessionOptional = sessionRepository.findByTokenAndUser_Id(token, userId);

        // Step 2: If no session is found in the database, return ENDED (the session no longer exists)
        if (sessionOptional.isEmpty()) {
            return SessionStatus.ENDED;
        }

        // Step 3: Retrieve the session object from the Optional
        Session session = sessionOptional.get();

        // Step 4: If the session is not ACTIVE, return INVALID (session exists but is no longer active)
        if (!session.getSessionStatus().equals(SessionStatus.ACTIVE)) {
            return SessionStatus.ENDED;
        }

        // Step 5: Validate the token's structure and signature using JJWT
        // This ensures the token is valid, has not been manipulated, and is signed with the correct secret key
        Jws<Claims> claimsJws = Jwts
                .parser() // Initialize the parser for parsing the token
                .verifyWith(secretKey) // Provide the secret key for signature verification
                .build() // Build the parser
                .parseSignedClaims(token); // Parse and validate the token, returning its claims

        // Step 6: Extract specific claims from the token payload (e.g., email)
        String email = (String) claimsJws.getPayload().get("email");

        // Step 7: Uncomment this block to extract and check the expiration timestamp if required
        // Long expiryAt = (Long) claimsJws.getPayload().get("expiryAt");

        // Step 8: Uncomment and implement expiration logic if necessary
        // Check if the token has expired based on the 'expiryAt' claim
        // if (new Date().getTime() > expiryAt) {
        //     // If the token's expiration time is in the past, consider it expired
        //     return SessionStatus.INVALID;
        // }

        // Step 9: If all checks pass, the session is valid and ACTIVE
        return SessionStatus.ACTIVE;
    }


/*
    public SessionStatus validate(String token, Long userId) {
        // Step 1: Find the session by token and userId
        Optional<Session> sessionOptional = sessionRepository.findByTokenAndUser_Id(token, userId);

        if (sessionOptional.isEmpty()) {
            //log.error("No session found for token: {} and userId: {}", token, userId);
            return SessionStatus.ENDED;
        }

        Session session = sessionOptional.get();

        // Step 2: Check if the session status is ACTIVE
        if (!SessionStatus.ACTIVE.equals(session.getSessionStatus())) {
            //log.error("Session status is not ACTIVE for userId: {}, token: {}", userId, token);
            return SessionStatus.INVALID;
        }

        try {
            // Step 3: Parse and validate the JWT token
            Jws<Claims> claimsJws = Jwts
                    .parser()
                    .verifyWith(secretKey)
                    .build()
                    .parseSignedClaims(token);

            // Extract claims (e.g., email and expiryAt)
            String email = (String) claimsJws.getPayload().get("email");
            //Long expiryAt = (Long) claimsJws.getPayload().get("expiryAt");

//            // Step 4: Check if the token has expired
//            if (new Date().getTime() > expiryAt) {
//                //log.error("Token has expired for userId: {}, email: {}", userId, email);
//                return SessionStatus.INVALID;
//            }

            //log.info("Token successfully validated for userId: {}, email: {}", userId, email);
            return SessionStatus.ACTIVE;
        } catch (JwtException ex) {
            // Step 5: Handle invalid tokens
            //log.error("Invalid token for userId: {}, error: {}", userId, ex.getMessage());
            return SessionStatus.INVALID;
        }
    }

 */



}


/*

The HTTP headers in a response are implemented as a MultiValueMap because:

Headers Can Have Multiple Values:
HTTP allows the same header name to have multiple values. For example:
    Set-Cookie: auth-token=abcd1234
    Set-Cookie: session-id=xyz789

In such cases, a MultiValueMap is a suitable data structure because it allows mapping a single key (header name) to multiple values.
Here, MultiValueMapAdapter wraps a HashMap to allow storing multiple values for the Set-Cookie header.
 */