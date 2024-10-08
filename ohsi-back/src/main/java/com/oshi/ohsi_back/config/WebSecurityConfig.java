package com.oshi.ohsi_back.config;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import com.oshi.ohsi_back.filter.jwtAuthentification;
import lombok.RequiredArgsConstructor;
import java.io.IOException;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class WebSecurityConfig {

    private final jwtAuthentification jwtAuthentification;

    @Bean
public SecurityFilterChain configure(HttpSecurity httpSecurity) throws Exception {
    httpSecurity
        .cors(cors -> cors.disable())  // CORS 활성화
        .csrf(csrf -> csrf.disable())  // CSRF 비활성화
        .httpBasic(http -> http.disable())  // HTTP Basic 인증 비활성화
        .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))  // 세션 관리 비활성화
        .authorizeHttpRequests(auth -> auth
            .requestMatchers(HttpMethod.OPTIONS, "/**").permitAll()  // OPTIONS 메서드 허용
            .requestMatchers(HttpMethod.GET, "/").permitAll()
            .requestMatchers(HttpMethod.POST, "/api/user/**").permitAll()
            .requestMatchers(HttpMethod.GET, "/api/search", "/api/oshi", "/api/oshis/{oshiId}", "/api/oshis/{oshiId}/categories").permitAll()
            .anyRequest().authenticated()
        )
        .exceptionHandling(ex -> ex.authenticationEntryPoint(new FailedAuthenticationEntryPoint()));

    httpSecurity.addFilterBefore(jwtAuthentification, UsernamePasswordAuthenticationFilter.class);
    return httpSecurity.build();
}

}

class FailedAuthenticationEntryPoint implements AuthenticationEntryPoint {

    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response,
                         AuthenticationException authException) throws IOException, ServletException {
        response.setContentType("application/json");
        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        response.getWriter().write("{\"code\": \"AF\", \"message\": \"Authorization Failed.\"}");
    }
}