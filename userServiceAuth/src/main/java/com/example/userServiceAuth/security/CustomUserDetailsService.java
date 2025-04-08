package com.example.userServiceAuth.security;

import com.example.userServiceAuth.models.User;
import com.example.userServiceAuth.repositories.UserRepository;
import com.example.userServiceAuth.security.models.CustomUserDetail;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    private UserRepository userRepository;

    public CustomUserDetailsService(UserRepository userRepository){
        this.userRepository = userRepository;
    }

    // implementation of our own UserDetails
    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException{
        Optional<User> userOptional = userRepository.findByEmail(userName);
        if(userOptional.isEmpty()){
            throw new UsernameNotFoundException("User not found Exception");
        }

        User user = userOptional.get();

        // provided implementation of CustomUserDetail
        return new CustomUserDetail(user);
    }
}
