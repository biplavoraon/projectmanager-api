package com.biplav.projectmanager.service;

import com.biplav.projectmanager.dto.AppUserResponse;
import com.biplav.projectmanager.model.AppUser;

public class UserToDto {
    private final AppUser user;

    public UserToDto(AppUser user) {
        this.user = user;
    }
    public static AppUserResponse userToDto(AppUser user)
    {
        return new AppUserResponse(
                user.getUserid(),
                user.getName(),
                user.getUsername(),
                user.getRole()
        );
    }
}
