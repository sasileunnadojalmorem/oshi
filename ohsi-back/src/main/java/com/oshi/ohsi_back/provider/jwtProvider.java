package com.oshi.ohsi_back.provider;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Date;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Component
public class jwtProvider {

    @Value("${secret-key}")
    private String secretKey;

    public String create(String email) {
        Date expiredDate = Date.from(Instant.now().plus(1, ChronoUnit.HOURS));

        return Jwts.builder()
            .signWith(SignatureAlgorithm.HS256, secretKey)
            .setSubject(email)
            .setIssuedAt(new Date())
            .setExpiration(expiredDate)
            .compact();
    }

    public String validate(String jwt) {
        Claims claims = null;
        try {
            claims = Jwts.parser()
                    .setSigningKey(secretKey)
                    .parseClaimsJws(jwt)
                    .getBody();
        } catch (Exception e) {
            // Log the exception using a logging framework
            return null;
        }

        return claims.getSubject();
    }
}
