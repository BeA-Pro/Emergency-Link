package com.emergency_link.emergency_link.security.config;

import com.emergency_link.emergency_link.security.jwt.JwtRequestFilter;
import com.emergency_link.emergency_link.security.jwt.JwtUtil;
import com.emergency_link.emergency_link.security.jwt.LoginFilter;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfigurationSource;

import static org.springframework.security.config.Customizer.withDefaults;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {
    private final AuthenticationConfiguration authenticationConfiguration;
    private final JwtUtil jwtUtil;
    private final CorsConfigurationSource corsConfigurationSource;

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration configuration) throws Exception {
        return configuration.getAuthenticationManager();
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .cors(corsCustomizer -> corsCustomizer.configurationSource(corsConfigurationSource))
                .csrf(csrf -> csrf.disable())
                .formLogin((auth) -> auth.disable())
                .cors(withDefaults())
//                .httpBasic((auth) -> auth.disable())
                .authorizeHttpRequests(authz -> authz
                        .anyRequest().permitAll()
                )
                .addFilterBefore(new JwtRequestFilter(jwtUtil), LoginFilter.class) // 로그인 필터 앞에 JwtRequestFilter 추가
                .addFilterAt(new LoginFilter(authenticationManager(authenticationConfiguration), jwtUtil), UsernamePasswordAuthenticationFilter.class) // 필터 추가, LoginFilter()는 인자를 받음 (AuthenticationManager() 메소드에 authenticationConfiguration 객체를 넣어야 함) 따라서 등록 필요, UsernamePasswordAuthenticationFilter를 LoginFilter로 대체
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS)); // 세션 설정, STATELESS: 서버는 세션을 유지하지 않고 클라이언트는 매 요청마다 새로운 세션을 생성

        return http.build();
    }
}

//.authorizeHttpRequests(authz -> authz
//        .requestMatchers("/test","/test2","/api/join", "/api/login").permitAll()
//        .anyRequest().authenticated()
//        )
