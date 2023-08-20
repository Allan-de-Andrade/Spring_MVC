package com.allan.SpringMVC.services;

import com.allan.SpringMVC.models.DTOs.UserDTO;
import com.allan.SpringMVC.models.Entities.User;
import com.allan.SpringMVC.repositories.UserRepository;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService{

    private final UserRepository userRepository;
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }


    public Optional<User> findByUserName(String username){
        return userRepository.findByUsername(username);
    }

    public User saveUser(UserDTO userDTO){
        User user = new User();
        user.setUsername(userDTO.getUsername());
        user.setPassword(passwordEncoder(userDTO.getPassword()));
        return userRepository.save(user);
    }

    private String passwordEncoder(String password){
       return new BCryptPasswordEncoder().encode(password);
    }
}
