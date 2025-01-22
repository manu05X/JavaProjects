package com.example.userServiceAuth.services;

import com.example.userServiceAuth.dtos.UserDto;
import com.example.userServiceAuth.models.Role;
import com.example.userServiceAuth.models.User;
import com.example.userServiceAuth.repositories.RoleRepository;
import com.example.userServiceAuth.repositories.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class UserService {
    private UserRepository userRepository;
    private RoleRepository roleRepository;
    public UserService(UserRepository userRepository, RoleRepository roleRepository) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
    }
    public UserDto getUserDetails(Long userId) {
        return new UserDto(); // returning an empty user for now. Update this to fetch user details from the DB
    }
    public UserDto setUserRoles(Long userId, List<Long> roleIds) {
        Optional<User> userOptional = userRepository.findById(userId);
        List<Role> roles = roleRepository.findAllByIdIn(roleIds);
        if (userOptional.isEmpty()) {
            return null;
        }
        User user = userOptional.get();
        user.setRoles(Set.copyOf(roles));
        User savedUser = userRepository.save(user);
        return UserDto.from(savedUser);
    }
}