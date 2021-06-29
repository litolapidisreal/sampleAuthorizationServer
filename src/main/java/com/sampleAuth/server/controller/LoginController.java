package com.sampleAuth.server.controller;

import com.sampleAuth.server.models.APIResponse;
import com.sampleAuth.server.service.UserLoginService;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import static com.sampleAuth.server.enums.StringConstant.PUBLIC_KEY;
import static com.sampleAuth.server.enums.StringConstant.TOKEN;

@RestController
//@RequestMapping("/api/login")
@CrossOrigin(origins = "http://localhost:4200")
@Slf4j
public class LoginController {
    private final UserLoginService userLoginService;
    private final AuthenticationManager authenticationManager;

    public LoginController(final UserLoginService userLoginService,
                           final AuthenticationManager authenticationManager) {
        this.userLoginService = userLoginService;
        this.authenticationManager = authenticationManager;
    }

    @GetMapping("/validate")
    public ResponseEntity<?> createAuthenticationToken(){
        return ResponseEntity.ok(new APIResponse("Success", "200"));
    }

    @PostMapping("/authenticate")
    public ResponseEntity createAuthenticationToken(@RequestBody Map<String, String> authenticationRequest)
            throws Exception {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        authenticationRequest.get("username"),
                        authenticationRequest.get("password")));
        HttpHeaders responseHeaders = new HttpHeaders();
        final String jwt =  Jwts.builder()
                .setSubject(authentication.getName())
                .claim("authorities", authentication.getAuthorities())
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis()+ TimeUnit.MINUTES.toMillis(10)))
                .signWith(Keys.hmacShaKeyFor(PUBLIC_KEY.getBytes()))
                .compact();
        return ResponseEntity
                .ok()
                .headers(responseHeaders).body(new APIResponse(jwt,"200:Success"));
    }
}
