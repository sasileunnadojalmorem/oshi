package com.oshi.ohsi_back.filter;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import com.oshi.ohsi_back.provider.jwtProvider;

import lombok.RequiredArgsConstructor;

// 이 클래스는 JWT 인증을 처리하기 위한 필터로 사용됩니다. Spring의 @Component 어노테이션을 사용하여 
// 이 클래스를 Spring Bean으로 등록합니다.
// @RequiredArgsConstructor 어노테이션은 final 필드를 인자로 하는 생성자를 자동으로 생성해줍니다.
@Component
@RequiredArgsConstructor
public class jwtAuthentification extends OncePerRequestFilter {

    // jwtProvide 클래스의 인스턴스를 주입받아 사용합니다. 
    // 이 클래스는 JWT를 생성하고 검증하는 기능을 제공합니다.
    private final jwtProvider jwtProvide;

    // 이 메서드는 각 HTTP 요청에 대해 한 번씩 호출되며, 요청을 필터링하고, 필터 체인을 통해 다음 필터로 요청을 전달합니다.
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {

                try {
                    String token = parseBearerTokeb(request);
                    String email = jwtProvide.validate(token);
                    if(token == null){
                        filterChain.doFilter(request, response);
                        return;
                    }
                    if(email == null){
                        filterChain.doFilter(request, response);
                        return;
    
                    }
                    
                    AbstractAuthenticationToken authenticationToken = 
                        new UsernamePasswordAuthenticationToken(email, null, AuthorityUtils.NO_AUTHORITIES);
    
                    authenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request)) ;
                    SecurityContext securityContext = SecurityContextHolder.createEmptyContext();
                    securityContext.setAuthentication(authenticationToken);
                    SecurityContextHolder.setContext(securityContext);
                    
                } catch (Exception e) {
                    e.printStackTrace();

                }
                filterChain.doFilter(request, response);

        // 현재는 구현되지 않았지만, 이 메서드에서는 요청에서 JWT를 추출하고 검증한 후,
        // 인증된 요청만 다음 필터 또는 컨트롤러로 전달하도록 처리할 수 있습니다.
    }

    // 이 메서드는 HTTP 요청 헤더에서 "Authorization" 헤더 값을 파싱하여, 
    // Bearer 토큰을 추출합니다.
    private String parseBearerTokeb(HttpServletRequest request) {

        // "Authorization" 헤더의 값을 가져옵니다.
        String authorzation = request.getHeader("Authorization");

        // "Authorization" 헤더에 값이 있는지 확인합니다.
        boolean hasAuthorzation = StringUtils.hasText(authorzation);
        if (!hasAuthorzation) return null; // 헤더에 값이 없으면 null을 반환합니다.

        // "Authorization" 헤더가 "Bearer "로 시작하는지 확인합니다.
        boolean isBearer = authorzation.startsWith("Bearer ");
        if (!isBearer) return null; // "Bearer "로 시작하지 않으면 null을 반환합니다.

        // "Bearer "를 제거하고, 실제 JWT 토큰만 추출하여 반환합니다.
        String token = authorzation.substring(7);
        return token;
    }
}
