//package com.javaproject.security;
//
//import com.javaproject.common.BaseException;
//import com.javaproject.entity.User;
//import com.javaproject.repository.UserRepository;
//import com.javaproject.service.JwtService;
//import lombok.RequiredArgsConstructor;
//import org.springframework.security.authentication.AuthenticationProvider;
//import org.springframework.security.core.Authentication;
//import org.springframework.security.core.AuthenticationException;
//import org.springframework.security.core.GrantedAuthority;
//import org.springframework.stereotype.Component;
//
//import java.util.Collection;
//import java.util.Optional;
//
//import static com.javaproject.common.ErrorCode.INVALID_AUTH_TOKEN;
//import static com.javaproject.common.ErrorCode.INVALID_ID_PASSWORD;
//
//@Component
//@RequiredArgsConstructor
//public class JwtAuthenticationProvider implements AuthenticationProvider {
//    private final JwtService jwtService;
//    private final UserRepository userRepository;
//
//
//
//
//    @Override
//    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
//        String token = authentication.getCredentials().toString();
//        String userId = jwtService.getUserId(token);
//        User user = userRepository.findByUserId(userId);
//        if(!jwtService.validateToken(token) || user == null) {
//            throw new BaseException(INVALID_AUTH_TOKEN);
//        }
//        return new JwtAuthenticationProvider(user,token, user.getAuthorities());
//    }
//
//    @Override
//    public boolean supports(Class<?> authentication) {
//        return false;
//    }
//}
