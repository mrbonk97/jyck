package com.mrbonk97.ourmemory.configuration;

import com.mrbonk97.ourmemory.utils.JwtTokenUtils;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Slf4j
public class JwtTokenFilter extends OncePerRequestFilter {
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String header = request.getHeader("Authorization");
        //TODO 에러 변환
        if(header == null) { log.error("헤더 암호가 비어있음"); filterChain.doFilter(request,response); return; }
        if(!header.startsWith("Bearer ")) { log.error("Jwt 방식이 아님"); filterChain.doFilter(request,response); return; }

        String token = header.split(" ")[1];
        if(!JwtTokenUtils.validateToken(token)) { log.error("토큰이 만료됨"); filterChain.doFilter(request,response); return; }
        Long userId = JwtTokenUtils.extractSubject(token);

        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(userId, null, null);
        authenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
        SecurityContextHolder.getContext().setAuthentication(authenticationToken);

        log.info("JwtToken 인증 성공 유저: " + userId);
        filterChain.doFilter(request, response);
    }
}
