package com.gateway.gatewaymicroservice.service;

import com.gateway.gatewaymicroservice.repository.UserRepository;
import org.springframework.security.core.userdetails.*;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
public class UserService implements ReactiveUserDetailsService {

    protected final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    // Реализация метода findByUsername интерфейса ReactiveUserDetailsService
    @Override
    public Mono<UserDetails> findByUsername(String username) {
        return userRepository.findByLogin(username)
                .map(user -> User.builder()
                        .username(user.getUsername())
                        .password(user.getPassword())
                        .build());
    }
}
