package com.codewithzenas.store.services;

import com.codewithzenas.store.entities.User;
import com.codewithzenas.store.repositories.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class AuthService {

    private UserRepository userRepository;

    public User getLoggedUser() {
        var authentication = SecurityContextHolder.getContext().getAuthentication();
        var userId = (Long) authentication.getPrincipal();

       return userRepository.findById(userId).orElse(null);
    }
}
