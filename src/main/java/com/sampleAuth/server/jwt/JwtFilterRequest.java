package com.sampleAuth.server.jwt;

import com.google.common.base.Strings;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * ONLY ONCE PER REQUEST
 */
@Component
public class JwtFilterRequest extends OncePerRequestFilter {

    @Override
    protected void doFilterInternal(
            HttpServletRequest request,
            HttpServletResponse response,
            FilterChain filterChain) throws ServletException, IOException {
        String authorizationHeader = request.getHeader("authorization");
        if (Strings.isNullOrEmpty(authorizationHeader) || !authorizationHeader.startsWith("Bearer")) {
            filterChain.doFilter(request, response);
            return;
        }

        try {
            String token = authorizationHeader.replace("Bearer ", "");
            String secretKey = "CJfiorjoiwghorwehgbvsdaufwe8ywfgciowechnjklcjksdksdgvudsv";
            //extracts JWS value to be readable
            Claims jwsBody = Jwts.parser().setSigningKey(Keys.hmacShaKeyFor(secretKey.getBytes()))
                    .parseClaimsJws(token).getBody();
            String username = jwsBody.getSubject();
            List<Map<String, String>> authorities = (List<Map<String, String>>) jwsBody.get("authorities");
            Authentication authentication = new UsernamePasswordAuthenticationToken(
                    username, null, authorities.stream().map(stringStringMap -> new SimpleGrantedAuthority(stringStringMap.get("authority")))
                    .collect(Collectors.toSet()));
            SecurityContextHolder.getContext().setAuthentication(authentication);

        }catch (JwtException exp){
            System.out.println(exp);
            throw new IllegalStateException("Illegal Token observed");
        }
        // needed to create a return value in your request
        filterChain.doFilter(request, response);
    }
}
