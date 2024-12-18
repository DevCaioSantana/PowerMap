package com.example.powermap.config;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTDecodeException;
import com.example.powermap.model.user.User;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

@Service
public class TokenService {

    @Value("${api.security.token.secret}")
    private String secret;
    public String generateToken(User user){
        try{
            Algorithm algorithm = Algorithm.HMAC256(secret);
            String token = JWT.create()
                    .withIssuer("PowerMap")
                    .withSubject(user.getEmail())
                    .withExpiresAt(genExpirationDate())
                    .sign(algorithm);
            System.out.println("Token generate: " + token);
            return token;
        } catch (JWTCreationException exception){
            throw new RuntimeException("Error while genereting token",exception);
        }
    }

    public String validateToken(String token) {
        try {
            System.out.println("Token validate: " + token);
            if (token == null ) {
                throw new IllegalArgumentException("Token ausente ou malformado.");
            }

            Algorithm algorithm = Algorithm.HMAC256(secret);
            return JWT.require(algorithm)
                    .withIssuer("PowerMap")
                    .build()
                    .verify(token)
                    .getSubject();
        } catch (IllegalArgumentException | JWTDecodeException e) {
            System.err.println("Erro ao validar o token: " + e.getMessage());
            throw new RuntimeException("Token JWT inválido ou malformado.");
        }
    }

    private Instant genExpirationDate(){
        return LocalDateTime.now().plusHours(2).toInstant(ZoneOffset.of("-03:00"));
    }
}
