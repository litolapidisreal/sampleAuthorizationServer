package com.sampleAuth.server.security;

import com.sampleAuth.server.service.ClientRegistrationService;
import com.sampleAuth.server.service.UserLoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.JwtTokenStore;

import javax.sql.DataSource;

import static com.sampleAuth.server.enums.StringConstant.*;

@Configuration
public class OAuthServerConfiguration extends AuthorizationServerConfigurerAdapter {
    @Autowired
    BCryptPasswordEncoder passwordEncoder;
    @Autowired
    ClientRegistrationService registrationService;
    @Autowired
    DataSource dataSource;
    @Autowired
    AuthenticationManager authenticationManager;
    @Autowired
    UserLoginService userLoginService;

    private String cliendId = "sampleClient";
    private String clientSecret = "my-secret";

    @Bean
    public JwtAccessTokenConverter tokenConverter(){
        JwtAccessTokenConverter converter = new JwtAccessTokenConverter();
        converter.setSigningKey(PRIVATE_KEY);
        converter.setVerifierKey(PUBLIC_KEY);
        return converter;
    }

    @Bean
    public JwtTokenStore jwtTokenStore(){
        return new JwtTokenStore(tokenConverter());
    }


    @Override
    public void configure(AuthorizationServerSecurityConfigurer security) throws Exception {
        security.tokenKeyAccess("permitAll()").checkTokenAccess("isAuthenticated()");
    }

    @Override
    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
         clients.withClientDetails(registrationService);
    }

    @Override
    public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
        endpoints.authenticationManager(authenticationManager)
                .tokenStore(jwtTokenStore())
                .accessTokenConverter(tokenConverter())
                .userDetailsService(userLoginService);
    }
}
