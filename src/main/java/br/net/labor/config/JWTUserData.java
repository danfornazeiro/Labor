package br.net.labor.config;

import java.util.UUID;

public record JWTUserData(
        UUID userId,
        String email,
        String role
) {}