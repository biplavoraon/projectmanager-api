package com.biplav.projectmanager.repository;

import com.biplav.projectmanager.model.AppUser;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepo extends JpaRepository<AppUser, Integer> {
    boolean existsByUsername(String username);
    AppUser findByUsername(String username);
}
