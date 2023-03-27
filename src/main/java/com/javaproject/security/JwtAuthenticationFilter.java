//package com.javaproject.security;
//
//import com.javaproject.service.JwtService;
//import lombok.NoArgsConstructor;
//import org.apache.tomcat.util.net.openssl.ciphers.Authentication;
//import org.springframework.http.HttpHeaders;
//import org.springframework.stereotype.Component;
//import org.springframework.util.StringUtils;
//import org.springframework.web.filter.OncePerRequestFilter;
//
//import javax.servlet.FilterChain;
//import javax.servlet.ServletException;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import java.io.IOException;
//
//
//@Component
//public class JwtAuthenticationFilter extends OncePerRequestFilter {
//
//    private JwtService jwtService;
//    private JwtAuthenticationToken jwtAuthenticationToken;
//    @Override
//    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
//        String token = resolveToken(request);
//        try {
//            if(token != null && jwtService.validateToken(token)) {
//                Authentication auth = jwtAuthenticationToken;
//            }
//        }
//
//        filterChain.doFilter(request, response);
//    }
//
//    private String resolveToken(HttpServletRequest request) {
//        String bearerToken = request.getHeader(HttpHeaders.AUTHORIZATION);
//        if(StringUtils.hasText(bearerToken) && bearerToken.startsWith("Bearer ")) {
//            return bearerToken.substring(7);
//        }
//        return null;
//    }
//}
