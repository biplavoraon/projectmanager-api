package com.biplav.projectmanager.security;

import com.biplav.projectmanager.jwt.JwtAuthFilter;
import com.biplav.projectmanager.jwt.JwtTokenVerifier;
import com.biplav.projectmanager.utility.JwtConfig;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.List;

import static org.springframework.security.config.http.SessionCreationPolicy.STATELESS;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class AppSecurityConfig {
    private final AuthenticationManager authenticationManager;
    private final JwtConfig jwtConfig;
    private final String key;

    public AppSecurityConfig(AuthenticationManager authenticationManager,
                             JwtConfig jwtConfig,
                             @Value("${secret_key}") String key) {
        this.authenticationManager = authenticationManager;
        this.jwtConfig = jwtConfig;
        this.key = key;
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .cors(Customizer.withDefaults())
                .csrf(AbstractHttpConfigurer :: disable)
                .sessionManagement().sessionCreationPolicy(STATELESS)
                .and()
                .addFilter(new JwtAuthFilter(authenticationManager, jwtConfig, key))
                .addFilterAfter(new JwtTokenVerifier(key), JwtAuthFilter.class)
                .authorizeHttpRequests( auth -> auth
                        .requestMatchers(
                                "/register/**",
                                "/task/priority/**",
                                "/task/states/**",
                                "/token/refresh/**",
                                "/signout/**")
                        .permitAll()
                        .anyRequest().authenticated()
                );
        return http.build();
    }

    @Bean
    CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();
        configuration.setAllowedOrigins(List.of(jwtConfig.client()));
        configuration.setAllowedMethods(List.of("GET","POST", "PUT", "DELETE"));
        configuration.setAllowedHeaders(List.of("Authorization", "Content-Type"));
        configuration.setAllowCredentials(true);
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**",configuration);
        return source;
    }



}
