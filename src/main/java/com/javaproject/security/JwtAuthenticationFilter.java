package com.javaproject.security;

import com.javaproject.common.CommonUtils;
import com.javaproject.entity.User;
import com.javaproject.repository.UserRepository;
import com.javaproject.service.JwtService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;


@Component
@RequiredArgsConstructor
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    private final JwtService jwtService;
    private final UserRepository userRepository;

    private final List<String> EXCLUDE_URL = List.of("/v3/api-docs", "/swagger-ui/**", "/swagger-resources/**", "/swagger-ui.html", "/favicon.ico");

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String servletPath = request.getServletPath();
        if (EXCLUDE_URL.stream().anyMatch(exclude -> CommonUtils.match(exclude, servletPath))) {
            filterChain.doFilter(request, response);
        } else {
            try {
                String token = resolveToken(request);
                String userId = jwtService.getUserId(token);
                User user = userRepository.findById(Long.parseLong(userId)).orElseThrow(Exception::new);
                if (token != null && jwtService.validateToken(token)) {
                    Authentication auth = new JwtAuthenticationToken(user, token, user.getAuthorities());
                    SecurityContextHolder.getContext().setAuthentication(auth);
                }
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                filterChain.doFilter(request, response);
            }
        }
    }

    private String resolveToken(HttpServletRequest request) {
        String bearerToken = request.getHeader(HttpHeaders.AUTHORIZATION);
        if (StringUtils.hasText(bearerToken) && bearerToken.startsWith("Bearer ")) {
            return bearerToken.substring(7);
        }
        return null;
    }
}
