package com.example.elibrary_management_system.config;

import com.example.elibrary_management_system.models.Authority;
import com.example.elibrary_management_system.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    @Autowired
    UserService userService;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Bean
    public AuthenticationManager authenticationManager(UserService userService,
                                                       PasswordEncoder passwordEncoder) {
        DaoAuthenticationProvider daoAuthenticationProvider = new DaoAuthenticationProvider();
        daoAuthenticationProvider.setUserDetailsService(userService);
        daoAuthenticationProvider.setPasswordEncoder(passwordEncoder);

        return new ProviderManager(daoAuthenticationProvider);
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf(csrf -> csrf.disable())
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/book/create").hasAuthority(Authority.ADMIN.name())
                        .requestMatchers("/book/get/**").hasAnyAuthority(Authority.ADMIN.name(),Authority.STUDENT.name())
                        .requestMatchers("/student/add").permitAll()
                        .requestMatchers("/student/admin/get").hasAuthority(Authority.ADMIN.name())
                        .requestMatchers("/admin/**").hasAuthority(Authority.ADMIN.name())
                        .requestMatchers("/student/get").hasAuthority(Authority.STUDENT.name())
                        .requestMatchers("/transactions/**").hasAuthority(Authority.STUDENT.name()))
                .httpBasic(Customizer.withDefaults());

        return http.build();
    }
}
