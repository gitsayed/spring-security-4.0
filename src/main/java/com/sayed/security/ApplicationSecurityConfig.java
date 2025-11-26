package com.sayed.security;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class ApplicationSecurityConfig {


    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) {
        http.authorizeHttpRequests(auth -> auth
                .requestMatchers("/",
                        "/index",
                        "/css/*",
                        "/js/*").permitAll()
                .anyRequest().authenticated()
        ).httpBasic(Customizer.withDefaults());

        return http.build();
    }

}
