package com.sayed.security;


import com.sayed.dto.RolePermission;
import com.sayed.dto.UserRole;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@RequiredArgsConstructor
public class ApplicationSecurityConfig {


    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) {
        http.csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/",
                                "/index",
                                "/css/*",
                                "/js/*").permitAll()
                        .requestMatchers("/employee").hasAllAuthorities(RolePermission.EMP_READ.getPermission(), RolePermission.EMP_WRITE.getPermission())
                        .requestMatchers("/management").hasAllAuthorities(RolePermission.COURSE_READ.getPermission(), RolePermission.COURSE_WRITE.getPermission())
                        .anyRequest().authenticated())
                .formLogin(Customizer.withDefaults())
                .httpBasic(Customizer.withDefaults());
        return http.build();
    }

    @Bean
    protected UserDetailsService userDetailsService() {
        UserDetails admin = User.builder().username("admin")
                .password(passwordEncoder().encode("admin"))
//                .roles(UserRole.ADMIN.name())
                .authorities(UserRole.ADMIN.getGrantedAuthorities())
                .build();
        UserDetails akib = User.builder().username("akib")
                .password(passwordEncoder().encode("akib1"))
//                .roles(UserRole.EMP.name())
                .authorities(UserRole.EMP.getGrantedAuthorities())
                .build();
        UserDetails sakib = User.builder().username("sakib")
                .password(passwordEncoder().encode("sakib1"))
                .roles(UserRole.EMP.name())
                .authorities(UserRole.EMP.getGrantedAuthorities())
                .build();
        return new InMemoryUserDetailsManager(admin, akib, sakib);
    }


    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder(10);
    }



/*
    // Default config written in the system
    @Configurati on(
            proxyBeanMethods = false
    )
    @ConditionalOnDefaultWebSecurity
    static class SecurityFilterChainConfiguration {
        @Bean
        @Order(2147483642)
        SecurityFilterChain defaultSecurityFilterChain(HttpSecurity http) {
            http.authorizeHttpRequests((requests) -> ((AuthorizeHttpRequestsConfigurer.AuthorizedUrl)requests.anyRequest()).authenticated());
            http.formLogin(Customizer.withDefaults());
            http.httpBasic(Customizer.withDefaults());
            return (SecurityFilterChain)http.build();
        }
    }

 */


}
