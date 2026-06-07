package br.net.labor.config;

import br.net.labor.model.user.User;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Optional;
import java.util.UUID;

@Service
public class TokenService {

    @Value("${jwt.secret}")
    private String secret;

    public String generateToken(User user){
        Algorithm algorithm = Algorithm.HMAC256(secret);
        return JWT
                .create()
                .withSubject(user.getEmail())
                .withClaim("userId", user.getId().toString())
                .withClaim("role", user.getRole().name())
                .withIssuedAt(Instant.now())
                .withExpiresAt(Instant.now().plus(1, ChronoUnit.DAYS))
                .sign(algorithm);
    }

    public Optional<JWTUserData> validateToken(String token){
        Algorithm algorithm = Algorithm.HMAC256(secret);
        try {
            DecodedJWT decodedJWT = JWT
                    .require(algorithm)
                    .build().verify(token);
            return Optional.of(
                    new JWTUserData(
                            UUID.fromString(
                                    decodedJWT.getClaim("userId").asString()
                            ),
                            decodedJWT.getSubject(),
                            decodedJWT.getClaim("role").asString()
                    )
            );
        } catch (JWTVerificationException e) {
           return Optional.empty();
        }
    }

}
