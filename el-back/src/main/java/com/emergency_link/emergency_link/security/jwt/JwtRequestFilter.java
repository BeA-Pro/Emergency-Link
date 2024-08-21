package com.emergency_link.emergency_link.security.jwt;

import com.emergency_link.emergency_link.entity.UserInfo;
import com.emergency_link.emergency_link.security.auth.CustomUserDetails;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;


import java.io.IOException;

public class JwtRequestFilter extends OncePerRequestFilter {
    private final JwtUtil jwtUtil;

    public JwtRequestFilter(JwtUtil jwtUtil) {
        this.jwtUtil = jwtUtil;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
            throws ServletException, IOException {

//        // 로그인 경로는 필터를 통과하지 않도록 설정
//        if (request.getServletPath().equals("/api/login")) {
//            chain.doFilter(request, response);
//            return;
//        }

        final String authorizationHeader = request.getHeader("Authorization");

        if (authorizationHeader == null || !authorizationHeader.startsWith("Bearer ")) {
            System.out.println("token null");
            chain.doFilter(request, response);
            return;
        }

        String token = authorizationHeader.substring(7);

        if (jwtUtil.isTokenExpired(token)) { // 토큰 만료
            System.out.println("token expired");

            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);

            response.setContentType("application/json");
            response.getWriter().write("{\"message\": \"Expired JWT token\"}");

            //조건이 해당되면 메소드 종료 (필수)
            return;

        }

        String userId = jwtUtil.extractUsername(token);
        String role = jwtUtil.extractRole(token);
        String password = jwtUtil.extractPassword(token);

        if (!jwtUtil.validateToken(token, userId)) { // 유효하지 않은 토큰
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);

            response.setContentType("application/json");
            response.getWriter().write("{\"message\": \"Invalid JWT token\"}");
            return;
        }

//        if (authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) {
//            jwt = authorizationHeader.substring(7);
//            username = jwtUtil.extractUsername(jwt);
//        }
//
//
//        if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
//
//            if (jwtUtil.validateToken(jwt, username)) {
//                UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(
//                        username, null, new ArrayList<>());
//                usernamePasswordAuthenticationToken
//                        .setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
//                SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
//            }
//        }
//        chain.doFilter(request, response);

        UserInfo userInfo = new UserInfo();
        userInfo.setUserId(userId);
        userInfo.setUserPwd(password);
        userInfo.setRole(role);

        CustomUserDetails customUserDetails = new CustomUserDetails(userInfo);

        // 스프링 시큐리티 인증 토큰 생성
        Authentication authToken = new UsernamePasswordAuthenticationToken(customUserDetails, null, customUserDetails.getAuthorities());

        // 세션에 사용자 등록
        SecurityContextHolder.getContext().setAuthentication(authToken);

        chain.doFilter(request, response); // 다음 필터로 요청 전달

    }
}
