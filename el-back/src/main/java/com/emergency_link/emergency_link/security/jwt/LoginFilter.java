package com.emergency_link.emergency_link.security.jwt;

import com.emergency_link.emergency_link.security.auth.CustomUserDetails;
import jakarta.servlet.FilterChain;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import java.util.Collection;
import java.util.Iterator;

public class LoginFilter extends UsernamePasswordAuthenticationFilter {
    private final AuthenticationManager authenticationManager;

    private final JwtUtil jwtUtil;

    public LoginFilter(AuthenticationManager authenticationManager, JwtUtil jwtUtil) {
        this.authenticationManager = authenticationManager;
        this.jwtUtil = jwtUtil;
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
        // request로부터 사용자의 이름과 비밀번호를 가져옴 (UsernamePasswordAuthenticationToken의 토큰을 검증하기 위해 AuthenticationManager의 authentication을 수행함
        String userId = obtainUsername(request);
        String password = obtainPassword(request);

        // 스프링시큐리티에서 usernamer과 password를 검증하기 위해 토큰에 담아야 함
        UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(userId, password);
        return authenticationManager.authenticate(authToken);
    }

    // 로그인 성공 시 메소드 : JWT 발급
    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication authentication) {
        System.out.println("login success");
        CustomUserDetails customUserDetails = (CustomUserDetails) authentication.getPrincipal(); // 사용자의 정보 추출

        String userId = customUserDetails.getUsername();
        String password = customUserDetails.getPassword();

        Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();
        Iterator<? extends GrantedAuthority> iterator = authorities.iterator();
        GrantedAuthority auth = iterator.next(); // 사용자의 권한 정보를 가져옴

        String role = auth.getAuthority();
        String token = jwtUtil.createToken(userId, role, password, 3600000L);
        System.out.println("token: " + token);
        System.out.println("userId: " + userId);
        response.addHeader("Authorization", "Bearer " + token);
    }

    //로그인 실패시 실행하는 메소드
    @Override
    protected void unsuccessfulAuthentication(HttpServletRequest request, HttpServletResponse response, AuthenticationException failed) {
        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        System.out.println("login fail");
    }
}
