package com.biplav.projectmanager.utility;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties("jwt")
public record JwtConfig(int hours, int days, String domain, String client) {
}
