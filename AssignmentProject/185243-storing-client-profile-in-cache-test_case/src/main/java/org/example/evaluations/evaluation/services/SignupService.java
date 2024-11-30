package org.example.evaluations.evaluation.services;

import org.example.evaluations.evaluation.exceptions.UserAlreadyExistsException;
import org.example.evaluations.evaluation.models.Client;
import org.example.evaluations.evaluation.repos.ClientRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Optional;

@Service
public class SignupService {

    @Autowired
    private ClientRepo clientRepo;


    @Autowired
    private RedisTemplate<String,Object> redisTemplate;

    public Client signup(String email, String password, String name, String phoneNumber) throws UserAlreadyExistsException {
        try {
            Optional<Client> optionalClient = clientRepo.findClientByEmail(email);
            if (optionalClient.isPresent()) throw new UserAlreadyExistsException("Please try out with some other email.");

            Client client = new Client();
            client.setName(name);
            client.setCreatedOn(new Date());
            client.setPassword(password);
            client.setEmail(email);
            client.setPhoneNumber(phoneNumber);
            redisTemplate.opsForHash().put("CLIENTS",client.getEmail(),client);
            clientRepo.save(client);

            return client;
        } catch (UserAlreadyExistsException exception) {
            throw exception;
        }
    }
}
