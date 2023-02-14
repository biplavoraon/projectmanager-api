package com.biplav.projectmanager.service;

import com.biplav.projectmanager.dto.UserAccount;
import com.biplav.projectmanager.model.AppUser;
import com.biplav.projectmanager.repository.UserRepo;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
public class AppUserService implements UserDetailsService {
    private final UserRepo userRepo;
    private final PasswordEncoder passwordEncoder;

    public AppUserService(UserRepo userRepo, PasswordEncoder passwordEncoder) {
        this.userRepo = userRepo;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        AppUser user = userRepo.findByUsername(username);
        UserAccount account;
        if (user != null) {
            Set<SimpleGrantedAuthority> grantedAuthorities = new HashSet<>();
            grantedAuthorities.add(new SimpleGrantedAuthority("ROLE_" + user.getRole()));
            account = new UserAccount(
                    grantedAuthorities,
                    user.getPassword(),
                    user.getUsername(),
                    true,
                    true,
                    true,
                    true
            );
        }
        else {
            throw new UsernameNotFoundException("User with username: " + username + " does not exist");
        }
        return account;
    }

}
