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
    private String registeredRedirectUris;//can be used for
    private String autoApproveScopes;//
    private String authorities;// same as UserInfo allowed actions
    private String accessTokenValiditySeconds;
    private String refreshTokenValiditySeconds;
    private String additionalInformation;

    public CustomClient() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getClientId() {
        return clientId;
    }

    public void setClientId(String clientId) {
        this.clientId = clientId;
    }

    public String getClientSecret() {
        return clientSecret;
    }

    public void setClientSecret(String clientSecret) {
        this.clientSecret = clientSecret;
    }

    public String getScope() {
        return scope;
    }

    public void setScope(String scope) {
        this.scope = scope;
    }

    public String getResourceIds() {
        return resourceIds;
    }

    public void setResourceIds(String resourceIds) {
        this.resourceIds = resourceIds;
    }

    public String getAuthorizedGrantTypes() {
        return authorizedGrantTypes;
    }

    public void setAuthorizedGrantTypes(String authorizedGrantTypes) {
        this.authorizedGrantTypes = authorizedGrantTypes;
    }

    public String getRegisteredRedirectUris() {
        return registeredRedirectUris;
    }

    public void setRegisteredRedirectUris(String registeredRedirectUris) {
        this.registeredRedirectUris = registeredRedirectUris;
    }

    public String getAutoApproveScopes() {
        return autoApproveScopes;
    }

    public void setAutoApproveScopes(String autoApproveScopes) {
        this.autoApproveScopes = autoApproveScopes;
    }

    public String getAuthorities() {
        return authorities;
    }

    public void setAuthorities(String authorities) {
        this.authorities = authorities;
    }

    public String getAccessTokenValiditySeconds() {
        return accessTokenValiditySeconds;
    }

    public void setAccessTokenValiditySeconds(String accessTokenValiditySeconds) {
        this.accessTokenValiditySeconds = accessTokenValiditySeconds;
    }

    public String getRefreshTokenValiditySeconds() {
        return refreshTokenValiditySeconds;
    }

    public void setRefreshTokenValiditySeconds(String refreshTokenValiditySeconds) {
        this.refreshTokenValiditySeconds = refreshTokenValiditySeconds;
    }

    public String getAdditionalInformation() {
        return additionalInformation;
    }

    public void setAdditionalInformation(String additionalInformation) {
        this.additionalInformation = additionalInformation;
    }
}
