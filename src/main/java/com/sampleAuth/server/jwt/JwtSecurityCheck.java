package com.sampleAuth.server.jwt;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sampleAuth.server.repository.UserRepository;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;
import java.util.Map;
import static com.sampleAuth.server.enums.StringConstant.TOKEN;

public class JwtSecurityCheck extends UsernamePasswordAuthenticationFilter{

    private final AuthenticationManager authenticationManager;
    private final UserRepository userRepository;
    public JwtSecurityCheck(AuthenticationManager authenticationManager, UserRepository userRepository) {
        this.authenticationManager = authenticationManager;
        this.userRepository = userRepository;
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request,
                                                HttpServletResponse response) throws AuthenticationException {
        Authentication authentication = null;
        try {
            Map<String, String> user = new ObjectMapper().readValue(
                    request.getInputStream(),
                    Map.class
            );
             authentication = new UsernamePasswordAuthenticationToken(
                    user.get("username"),
                    user.get("password")
            );
            authentication = authenticationManager.authenticate(authentication);
        }catch(IOException exp){
            authentication = new UsernamePasswordAuthenticationToken(
                    "username",
                    "password"
            );
            authentication = authenticationManager.authenticate(authentication);
        }
        return authentication;
    }

    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication authResult) throws IOException, ServletException {
        String jwtToken = Jwts.builder()
                .setSubject(authResult.getName())
                .claim("authorities", authResult.getAuthorities())
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis()+360000L))
                .signWith(Keys.hmacShaKeyFor(TOKEN.getBytes()))
                .compact();
        response.addHeader("Authorization", "Bearer " + jwtToken);
    }
}
