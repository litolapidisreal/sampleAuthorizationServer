package com.sampleAuth.server.models;

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
@NoArgsConstructor
public class Clients implements ClientDetails  {

    private String clientId;
    private String clientSecret;
    private Set<String> scope = Collections.emptySet();//ability that an api can use
    private Set<String> resourceIds = Collections.emptySet();//defines the api that will be used in scope or the resource "services" allowed in scope
    private Set<String> authorizedGrantTypes = Collections.emptySet();//types of common Oauth uses
    /**
     * 1. authorizationCode -> with login requirement
     * 2. PKCE -> for login with DDOS Security (read more) and token sharing in public client
     * 3. Client Credentials -> non login based Connection, no login needed
     * 4. Device Code -> for input constrained devices (read more)
     * 5. Refresh Token -> auto login for Remember Me option
     */
    private Set<String> registeredRedirectUris;//can be used for
    private Set<String> autoApproveScopes;//
    private List<GrantedAuthority> authorities = Collections.emptyList();// same as UserInfo allowed actions
    private Integer accessTokenValiditySeconds;
    private Integer refreshTokenValiditySeconds;
    private Map<String, Object> additionalInformation = new LinkedHashMap<String, Object>();


    @Override
    public String getClientId() {
        return this.clientId;
    }

    @Override
    public Set<String> getResourceIds() {
        return this.resourceIds;
    }

    @Override
    public boolean isSecretRequired() {
        return true;
    }

    @Override
    public String getClientSecret() {
        return this.clientSecret;
    }

    @Override
    public boolean isScoped() {
        return true;
    }

    @Override
    public Set<String> getScope() {
        return this.scope;
    }

    @Override
    public Set<String> getAuthorizedGrantTypes() {
        return this.authorizedGrantTypes;
    }

    @Override
    public Set<String> getRegisteredRedirectUri() {
        return this.registeredRedirectUris;
    }

    @Override
    public Collection<GrantedAuthority> getAuthorities() {
        return this.authorities;
    }

    @Override
    public Integer getAccessTokenValiditySeconds() {
        return this.accessTokenValiditySeconds;
    }

    @Override
    public Integer getRefreshTokenValiditySeconds() {
        return this.refreshTokenValiditySeconds;
    }

    @Override
    public boolean isAutoApprove(String scope) {
        return this.autoApproveScopes.contains(scope);
    }

    @Override
    public Map<String, Object> getAdditionalInformation() {
        return this.additionalInformation;
    }
}
