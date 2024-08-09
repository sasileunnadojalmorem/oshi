package com.oshi.ohsi_back.provider;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Date;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

// 이 클래스는 JWT를 생성하고 검증하는 기능을 제공합니다. Spring의 @Component 어노테이션을 사용하여 
// 이 클래스를 Spring Bean으로 등록합니다.
@Component
public class jwtProvide {

    // JWT를 서명(signing)하는 데 사용할 비밀 키(secret key)입니다. 
    // 이 키는 토큰의 무결성을 검증하는 데 사용됩니다.
    @Value("{secret-key}")
    private String secretkey ;

    // 사용자의 이메일을 인자로 받아 JWT를 생성하는 메서드입니다.
    public String create(String email){

        // JWT의 만료 시간을 현재 시간으로부터 1시간 후로 설정합니다.
        Date expiredDate = Date.from(Instant.now().plus(1, ChronoUnit.HOURS));

        // JWT를 생성합니다.
        // .signWith() 메서드를 사용하여 지정된 알고리즘과 비밀 키를 사용해 서명합니다.
        // .setSubject()는 이 JWT의 주체(subject)를 설정하는데, 여기서는 사용자의 이메일을 주체로 설정합니다.
        // .setIssuedAt()은 JWT가 발행된 시간을 설정합니다.
        // .setExpiration()은 JWT의 만료 시간을 설정합니다.
        String jwt = Jwts.builder()
            .signWith(SignatureAlgorithm.ES256, secretkey)
            .setSubject(email)
            .setIssuedAt(new Date())
            .setExpiration(expiredDate)
            .compact();

        // 생성된 JWT를 반환합니다.
        return jwt;
    }

    // JWT를 검증하고, 그 안에 포함된 주체(subject)를 추출하는 메서드입니다.
    public String validate(String jwt){
        Claims claims = null;
        try {
            // Jwts.parser()를 사용해 JWT 파서를 생성하고,
            // .setSigningKey()로 서명 검증에 사용할 비밀 키를 설정합니다.
            // .parseClaimsJws()로 전달된 JWT의 서명과 만료 시간 등을 검증하고,
            // JWT의 페이로드(payload)에서 클레임(claims)을 추출합니다.
            claims = Jwts.parser().setSigningKey(secretkey)
                    .parseClaimsJws(jwt).getBody();
        } catch (Exception e) {
            // JWT가 유효하지 않거나 서명 검증에 실패하면 예외가 발생하고, 
            // 예외 정보를 출력합니다. null을 반환하여 유효하지 않은 JWT임을 나타냅니다.
            e.printStackTrace();
            return null;
        }

        // 유효한 JWT일 경우 그 주체(subject)를 반환합니다.
        return claims.getSubject();
    }
}
