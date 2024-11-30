package org.example.evaluations.evaluation.services;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtParser;
import io.jsonwebtoken.Jwts;
import org.example.evaluations.evaluation.models.User;
import org.example.evaluations.evaluation.repos.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import javax.crypto.SecretKey;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Service
public class AuthenticationService {

    @Autowired
    private RedisTemplate<String,Object> redisTemplate;

    @Autowired
    private SecretKey secretKey;

    @Autowired
    private UserRepo userRepo;

    public void setSecretKey(SecretKey secretKey) {
        this.secretKey = secretKey;
    }

    public ResponseEntity<Boolean> login(String email, String password) {
        Optional<User> userOptional = userRepo.findUserByEmail(email);

        if(userOptional.isEmpty() || !userOptional.get().getPassword().equals(password)) {
            return new ResponseEntity<>(false,HttpStatus.BAD_REQUEST);
        }

        Map<String,Object> claims =new HashMap<>();
        claims.put("user",email);
        long timeInMillis = System.currentTimeMillis();
        claims.put("iat",timeInMillis);
        claims.put("exp",timeInMillis+86400000);

        String token = Jwts.builder().claims(claims).signWith(secretKey).compact();

        redisTemplate.opsForValue().set(email, token);

        MultiValueMap<String,String> headers = new LinkedMultiValueMap<>();
        headers.add(HttpHeaders.SET_COOKIE,token);

        return new ResponseEntity(true,headers, HttpStatus.OK);
    }

    public Boolean validateToken(String email,String token) {
        String storedToken = (String) redisTemplate.opsForValue().get(email);
        if(storedToken == null) {
            throw new RuntimeException("Authentication Error !!");
        }

        JwtParser jwtParser = Jwts.parser().verifyWith(secretKey).build();
        Claims claims = jwtParser.parseSignedClaims(token).getPayload();

        Long expiry = (Long)claims.get("exp");
        Long currentTimeInMillis = System.currentTimeMillis();

        if(currentTimeInMillis > expiry) {
            System.out.println("Token Expired");
            return false;
        }

        return true;
    }
}
