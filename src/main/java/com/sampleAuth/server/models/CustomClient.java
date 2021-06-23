package com.sampleAuth.server.models;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.oauth2.provider.ClientDetails;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.*;

@Setter
@Getter
@NoArgsConstructor
@Entity
public class CustomClient {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)

    private Long id;
    private String clientId;
    private String clientSecret;
    private String scope;//ability that an api can use
    private String resourceIds;//defines the api that will be used in scope or the resource "services" allowed in scope
    private String authorizedGrantTypes;//types of common Oauth uses
    /**
     * 1. authorizationCode -> with login requirement
     * 2. PKCE -> for login with DDOS Security (read more) and token sharing in public client
     * 3. Client Credentials -> non login based Connection, no login needed
     * 4. Device Code -> for input constrained devices (read more)
     * 5. Refresh Token -> auto login for Remember Me option
     */
    private String registeredRedirectUris;//can be used for
    private String autoApproveScopes;//
    private String authorities;// same as UserInfo allowed actions
    private String accessTokenValiditySeconds;
    private String refreshTokenValiditySeconds;
    private String additionalInformation; 
}
