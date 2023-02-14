package com.biplav.projectmanager.dto;


import com.biplav.projectmanager.validator.*;

public class AppUserRequest {
    @ValidName(message =
                "2 to 24 characters. " +
                "Must begin and end with a letter. " +
                "Letters, spaces, dots, commas, apostrophes allowed.")
    private String name;
    @Unique(message = "Username is already taken")
    @ValidUsername(message =
                    "4 to 24 characters. " +
                    "Must begin with a letter. " +
                    "Letters, numbers, underscores, hyphens allowed.")
    private String username;
    @ValidPassword(message =
                    "8 to 24 characters. " +
                    "Must include uppercase and lowercase letters, a number and a special character. " +
                    "Allowed special characters: ! @ # $ % ")
    private String password;
    @ValidRole(message = "Role must be USER or ADMIN")
    private String role;

    public AppUserRequest(String name, String username, String password, String role) {
        this.name = name;
        this.username = username;
        this.password = password;
        this.role = role;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
}
