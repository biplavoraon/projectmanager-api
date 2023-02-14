package com.biplav.projectmanager.service;

import com.biplav.projectmanager.dto.AppUserRequest;
import com.biplav.projectmanager.dto.AppUserResponse;
import com.biplav.projectmanager.exception.UserNotFoundException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;

public interface UserService {
    AppUserResponse saveUser(AppUserRequest user);
    List<AppUserResponse> getAllUsers();
    AppUserResponse getUserById(int id) throws UserNotFoundException;
    String deleteUser(int id);

    void refreshToken(HttpServletRequest request, HttpServletResponse response) throws IOException;

    void logout(HttpServletRequest request, HttpServletResponse response);
}
