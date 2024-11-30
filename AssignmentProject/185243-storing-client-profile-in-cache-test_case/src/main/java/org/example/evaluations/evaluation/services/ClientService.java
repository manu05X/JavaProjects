package org.example.evaluations.evaluation.services;

import org.example.evaluations.evaluation.exceptions.UserNotFoundException;
import org.example.evaluations.evaluation.models.Client;
import org.example.evaluations.evaluation.repos.ClientRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ClientService {
    @Autowired
    private ClientRepo clientRepo;

    @Autowired
    private RedisTemplate<String,Object> redisTemplate;

    public Client getClientFromId(Long id) throws UserNotFoundException {

        Optional<Client> clientOptional = clientRepo.findById(id);
        if(clientOptional.isEmpty()) {
            throw new UserNotFoundException("Please signup first");
        }

        return clientOptional.get();
    }

    public Client getClientFromEmail(String email) throws UserNotFoundException {
        Client client = (Client) redisTemplate.opsForHash().get("CLIENTS",email);

        if (client == null) {
            throw new UserNotFoundException("Please signup first");
        }

        return client;
    }
}
