package com.senac.viverbem.infra.security;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.senac.viverbem.domain.user.UserModel;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

@Service
public class TokenService {

    public String generateToken(UserModel user){
        try{
            Algorithm algorithm = Algorithm.HMAC256("TESTE");
            String token = JWT.create()
                    .withIssuer("viverbem-auth-api")
                    .withSubject(user.getUsername())
                    .withExpiresAt(genExpirationDate())
                    .sign(algorithm);
            return token;
        } catch (JWTCreationException err){
            throw new RuntimeException("Erro ao gerar authentication token", err);
        }
    }

    public String validateToken(String token){
        try{
            Algorithm algorithm = Algorithm.HMAC256("TESTE");
            return JWT.require(algorithm)
                    .withIssuer("viverbem-auth-api")
                    .build()
                    .verify(token)
                    .getSubject();
        } catch (JWTVerificationException err){
            return "";
        }
    }

    private Instant genExpirationDate(){
        return LocalDateTime.now().plusHours(2).toInstant(ZoneOffset.of("-03:00"));
    }
}
