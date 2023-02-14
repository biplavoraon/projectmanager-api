package com.biplav.projectmanager.service;

import com.biplav.projectmanager.dto.AppUserRequest;
import com.biplav.projectmanager.dto.AppUserResponse;
import com.biplav.projectmanager.exception.UserNotFoundException;
import com.biplav.projectmanager.model.AppUser;
import com.biplav.projectmanager.repository.UserRepo;
import com.biplav.projectmanager.utility.JwtConfig;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.base.Strings;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

import static com.biplav.projectmanager.service.UserToDto.userToDto;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@Service
public class UserServiceImpl implements UserService{
    private final UserRepo userRepo;
    private final PasswordEncoder passwordEncoder;
    private final AppUserService appUserService;
    private final JwtConfig jwtConfig;
    @Value("${secret_key}")
    private String key;

    public UserServiceImpl(UserRepo userRepo,
                           PasswordEncoder passwordEncoder,
                           AppUserService appUserService,
                           JwtConfig jwtConfig) {
        this.userRepo = userRepo;
        this.passwordEncoder = passwordEncoder;
        this.appUserService = appUserService;
        this.jwtConfig = jwtConfig;
    }

    @Override
    public AppUserResponse saveUser(AppUserRequest userRequest) {
        AppUser user = new AppUser(
                0,
                userRequest.getName(),
                userRequest.getUsername(),
                passwordEncoder.encode(userRequest.getPassword()),
                userRequest.getRole(),
                new ArrayList<>()
        );
        return userToDto(userRepo.save(user));
    }
    @Override
    public List<AppUserResponse> getAllUsers() {
        List<AppUser> users = userRepo.findAll();
        return users
            .stream()
            .map(UserToDto::userToDto)
            .collect(Collectors.toList());
    }

    @Override
    public AppUserResponse getUserById(int id) throws UserNotFoundException {
        AppUser user = userRepo.findById(id)
                .orElseThrow(() -> new UserNotFoundException(
                        "User with id:" + id + " does not exist"));
        return userToDto(user);
    }

    @Override
    public String deleteUser(int id) {
        userRepo.deleteById(id);
        return ("User deleted");
    }

    @Override
    public void refreshToken(HttpServletRequest request, HttpServletResponse response) throws IOException {
        if (request.getCookies() == null)
            return;
        String headerName = "Authorization";
        String authHeader = Arrays.stream(request.getCookies())
                .filter(cookie -> headerName.equals(cookie.getName()))
                .map(Cookie::getValue)
                .findAny()
                .orElseThrow(IOException :: new);

        if (Strings.isNullOrEmpty(authHeader) || !authHeader.startsWith("Bearer"))
        {
            return;
        }
        String refreshToken = authHeader.substring(7);
        try
        {
            Jws<Claims> claims = Jwts
                    .parserBuilder()
                    .setSigningKey(Keys.hmacShaKeyFor(key.getBytes()))
                    .build()
                    .parseClaimsJws(refreshToken);

            Claims body = claims.getBody();
            String username = body.getSubject();
            UserDetails user = appUserService.loadUserByUsername(username);

            String accessToken = Jwts
                    .builder()
                    .setSubject(user.getUsername())
                    .claim("authorities", user.getAuthorities())
                    .setIssuedAt(new Date())
                    .setExpiration(java.sql.Timestamp.valueOf(LocalDateTime.now().plusHours(jwtConfig.hours())))
                    .signWith(Keys.hmacShaKeyFor(key.getBytes(StandardCharsets.UTF_8)))
                    .compact();

            HashMap<String, String> token = new HashMap<>();
            token.put("accessToken", "Bearer " + accessToken);
            response.setContentType(APPLICATION_JSON_VALUE);
            new ObjectMapper().writeValue(response.getOutputStream(), token);
        }
        catch (JwtException e)
        {
            throw new IllegalStateException("Refresh Token: " + refreshToken + " cannot be trusted");
        }
    }

    @Override
    public void logout(HttpServletRequest request, HttpServletResponse response) {
        if (request.getCookies() == null)
            return;

        Cookie cookie = new Cookie("Authorization", null);
        cookie.setMaxAge(0);
        response.addCookie(cookie);
    }
}
