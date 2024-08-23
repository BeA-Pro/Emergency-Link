package com.emergency_link.emergency_link.security.jwt;

import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

@Getter
@Component
public class JwtUtil {
    private SecretKey secretKey;
    private int expiredMs;


    // application-secret.yml에서 secret 값 가져와서 HS256을 사용하여 key에 저장
    public JwtUtil(@Value("${jwt.secretKey}") String secretKey) {
        this.secretKey = new SecretKeySpec(secretKey.getBytes(StandardCharsets.UTF_8), SignatureAlgorithm.HS256.getJcaName());
    }

    public String extractUsername(String token) {
        return extractClaim(token, claims -> claims.get("username", String.class));
    }

    public String extractRole(String token) {
        return extractClaim(token, claims -> claims.get("role", String.class));
    }

    public String extractPassword(String token) {
        return extractClaim(token, claims -> claims.get("password", String.class));
    }

    public Date extractExpiration(String token) {
        return extractClaim(token, Claims::getExpiration);
    }

    public <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = extractAllClaims(token);
        return claimsResolver.apply(claims);
    }

    private Claims extractAllClaims(String token) {
        return Jwts.parser() // 최신 라이브러리에서는 parserBuilder 사용
                .setSigningKey(secretKey) // Key를 설정
                .parseClaimsJws(token)
                .getBody();
    }

    public Boolean isTokenExpired(String token) {
        return extractExpiration(token).before(new Date());
    }

    public String createToken(String userId, String role, String userPwd, Long expiredMs) {
        // JWT에 담을 클레임을 정의합니다.
        return Jwts.builder()
                .claim("role", role)          // 역할을 클레임에 추가합니다.
                .claim("username", userId)        // 서브젝트에 사용자 이름을 설정합니다.
                .claim("password", userPwd)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + expiredMs))
                .signWith(SignatureAlgorithm.HS256, secretKey)  // 서명 알고리즘과 비밀 키를 설정합니다.
                .compact();
    }

    public Boolean validateToken(String token, String userId) {
        final String extractedUserId = extractUsername(token);
        return (extractedUserId.equals(userId) && !isTokenExpired(token));
    }
}