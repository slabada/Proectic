package com.gateway.gatewaymicroservice.Config;

import com.gateway.gatewaymicroservice.Service.UserService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.ReactiveAuthenticationManager;
import org.springframework.security.authentication.UserDetailsRepositoryReactiveAuthenticationManager;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    protected final UserService userService;

    public SecurityConfig(UserService userService) {
        this.userService = userService;
    }

    // Конфигурация безопасности с использованием SecurityFilterChain
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests((requests) -> requests
                        .requestMatchers(
                                "/css/**",
                                "/js/**")
                        .permitAll() // Разрешение доступа к определенным URL-ам без аутентификации
                        .anyRequest()
                        .authenticated() // Все остальные URL-ы требуют аутентификации
                )
                .httpBasic(Customizer.withDefaults()); // Использование HTTP Basic Authentication

        return http.build();
    }

    // Конфигурация менеджера аутентификации
    @Bean
    public ReactiveAuthenticationManager authenticationManager() {
        UserDetailsRepositoryReactiveAuthenticationManager authenticationManager =
                new UserDetailsRepositoryReactiveAuthenticationManager(userService);

        // Использование NoOpPasswordEncoder для хранения паролей
        authenticationManager.setPasswordEncoder(NoOpPasswordEncoder.getInstance());

        return authenticationManager;
    }
}
