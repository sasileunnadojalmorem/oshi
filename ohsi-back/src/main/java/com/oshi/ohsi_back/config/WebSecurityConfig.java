package com.oshi.ohsi_back.config;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.context.annotation.Bean;
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

@Configurable
@EnableWebSecurity
@RequiredArgsConstructor

public class WebSecurityConfig {
    private final jwtAuthentification jwtAuthentification;
    @Bean
    protected SecurityFilterChain  configure(HttpSecurity HttpSecurity) throws Exception{
        HttpSecurity
            .cors().and()
            .csrf().disable()
            .httpBasic().disable()
            .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and()
            .authorizeRequests()
            .antMatchers("/","/api/user/**").permitAll()
            .antMatchers(HttpMethod.GET, 
                         "/api/user/{useremail}", 
                         "/api/search", 
                         "/api/oshi", 
                         "/api/oshis/{oshiId}", 
                         "/api/oshis/{oshiId}/categories", 
                         "/api/oshis/{oshiId}/categories/{categoryId}", 
                         "/api/oshis/{oshiId}/categories/{categoryId}/goods", 
                         "/api/goods/types", 
                         "/api/oshis/{oshiId}/categories/{categoryId}/goods/{goodsId}", 
                         "/api/images/{imageId}").permitAll()
            .anyRequest().authenticated().and()
            .exceptionHandling().authenticationEntryPoint(new FailedAuthenticationEntryPoint());

        HttpSecurity.addFilterBefore(jwtAuthentification,UsernamePasswordAuthenticationFilter.class);
        return HttpSecurity.build();


            //croos origin resuorce sharing 웹 페이지의 리소스가 다른 도메인에 상정할수있는 규칙
            //crsf 사용자가 데이터 위변조 사용자계정에서 악용성있는 작업 수행 세션 기반에서 처리

    }   
    

}


class FailedAuthenticationEntryPoint implements AuthenticationEntryPoint{

    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response,
            AuthenticationException authException) throws IOException, ServletException {
        response.setContentType("application/json");
        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        response.getWriter().write("{\"code\": \"AF\" \"message\" : \"Authorzation Failed.\"}");
    }
    
}

