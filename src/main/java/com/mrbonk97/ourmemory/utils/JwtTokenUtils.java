package com.mrbonk97.ourmemory.utils;

import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.util.Date;

@Component
public class JwtTokenUtils {

    @Value("${token.secret-key}")
    private String secretKey;
    private static Key key;
    private static final Long accessTokenExpireTime = 25900000L;
    private static final Long refreshTokenExpireTime = 2590000000L;

    @Value("${token.secret-key}")
    public void setSecretKey(String secretKey) {
        byte[] keyBytes = secretKey.getBytes(StandardCharsets.UTF_8);
        JwtTokenUtils.key = Keys.hmacShaKeyFor(keyBytes);
    }

    public static String generateToken(Long id) {
        return Jwts.builder()
                .setSubject(String.valueOf(id))
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + accessTokenExpireTime))
                .signWith(key, SignatureAlgorithm.HS256)
                .compact();

    }

    public static Long extractSubject(String token) {
        Claims claims = Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(token).getBody();
        return Long.valueOf(claims.getSubject());
    }

    public static Boolean validateToken(String token) {

        try {
            Claims claims = Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(token).getBody();
        } catch (ExpiredJwtException e) {
            return false;
        }
        return true;
    }
}
