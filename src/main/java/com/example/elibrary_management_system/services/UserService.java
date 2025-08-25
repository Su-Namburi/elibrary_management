package com.example.elibrary_management_system.services;

import com.example.elibrary_management_system.models.Authority;
import com.example.elibrary_management_system.models.User;
import com.example.elibrary_management_system.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService implements UserDetailsService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Override
    public User loadUserByUsername(String username) throws UsernameNotFoundException {

        User user = this.userRepository.findById(username).orElse(null);
        System.out.println("Loading user: " + user.getUsername());
        System.out.println("Authority in DB: " + user.getAuthorities());

        return user;
    }

    public User createUser(User user, Authority authority) {
        String encodedPassword = passwordEncoder.encode(user.getPassword());
        user.setPassword(encodedPassword);
        user.setAuthorities(authority);

        return this.userRepository.save(user);
    }

}
