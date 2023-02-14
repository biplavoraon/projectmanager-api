package com.biplav.projectmanager.controller;

import com.biplav.projectmanager.dto.AppUserRequest;
import com.biplav.projectmanager.dto.AppUserResponse;
import com.biplav.projectmanager.exception.UserNotFoundException;
import com.biplav.projectmanager.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@RestController
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/register")
    public ResponseEntity<AppUserResponse> addUser(@RequestBody @Valid AppUserRequest userRequest)
    {
        return new ResponseEntity<>(userService.saveUser(userRequest), HttpStatus.CREATED) ;
    }

    @GetMapping("/token/refresh")
    public void refreshToken(HttpServletRequest request, HttpServletResponse response) throws IOException
    {
        userService.refreshToken(request, response);
    }

    @GetMapping("/signout")
    public void logoutUser(HttpServletRequest request, HttpServletResponse response) {

        userService.logout(request, response);
    }
    @GetMapping("/user")
    public ResponseEntity<List<AppUserResponse>> getUsers()
    {

        return ResponseEntity.ok(userService.getAllUsers());
    }

    @PreAuthorize("hasAnyRole('ROLE_USER', 'ROLE_ADMIN')")
    @GetMapping("/user/{id}")
    public ResponseEntity<AppUserResponse> getUserById(
            @PathVariable("id") int id) throws UserNotFoundException
    {
        return ResponseEntity.ok(userService.getUserById(id));
    }

}
