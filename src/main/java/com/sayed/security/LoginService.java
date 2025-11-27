package com.sayed.security;


import com.sayed.dto.LoginRequestDto;
import com.sayed.dto.LoginResponseDto;
import com.sayed.entity.AppUser;
import com.sayed.jwt.JwtUtils;
import com.sayed.repository.AppUserRepository;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.experimental.Accessors;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class LoginService {

    private final AppUserRepository appUserRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtils jwtUtils;

    public LoginResponseDto doLogin(LoginRequestDto request) {
        AppUser user = getUser(request.getUsername());
        if (!passwordEncoder.matches(request.getPassword(), user.getPassword() )) {
            throw new BadCredentialsException("Bad credentials");
        }

        MetaAuthorities metaAuth = generateAuthentication(user);
        String token = jwtUtils.generateJwtToken(user, metaAuth.getRoles(), metaAuth.getAuthorities());

        LoginResponseDto responseDto = new LoginResponseDto();
        responseDto.setAccessToken(token)
                .setRefreshToken(token);
        return responseDto;
    }

    public void doAuthenticated(String username) {
        AppUser user = getUser(username);
        generateAuthentication(user);
    }

    private MetaAuthorities  generateAuthentication(AppUser user){
        List<String> roles = new ArrayList<>();
        Set<GrantedAuthority> authorities = new HashSet<>();

        user.getRoles().stream().forEach(role -> {
            Set<SimpleGrantedAuthority> permissions = role.getPermissions().stream()
                    .map(permission -> new SimpleGrantedAuthority(permission.getName()))
                    .collect(Collectors.toSet());
            permissions.add(new SimpleGrantedAuthority("ROLE_" + role.getName()));
            roles.add(role.getName());
            authorities.addAll(permissions);
        });

        Authentication authentication = new UsernamePasswordAuthenticationToken(user.getUsername(), null, authorities);
        SecurityContextHolder.getContext().setAuthentication(authentication);

        MetaAuthorities  metaAuthorities = new MetaAuthorities();
        metaAuthorities.setRoles(roles)
        .setAuthorities(authorities);
        return metaAuthorities;
    }

    private AppUser getUser(String username) {
        return appUserRepository.findTop1ByUsernameOrEmail(username, username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found by username: " + username));
    }

    @Data
    @Accessors(chain = true)
    class MetaAuthorities{
        private List<String> roles;
        private  Set<GrantedAuthority> authorities;

    }

}
