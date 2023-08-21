package com.allan.SpringMVC.security;

import com.allan.SpringMVC.models.Entities.User;
import com.allan.SpringMVC.services.UserService;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserServiceSecurity implements UserDetailsService {
    private final UserService userService;

    public UserServiceSecurity(UserService userService) {
        this.userService = userService;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> user = userService.findByUserName(username);

        if(user.isPresent()) {
            return user.get();
        }

        else
            throw new UsernameNotFoundException("This user was not found");
    }
}
