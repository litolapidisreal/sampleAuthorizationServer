package com.sampleAuth.server.service.impl;

import com.sampleAuth.server.models.Clients;
import com.sampleAuth.server.models.CustomClient;
import com.sampleAuth.server.service.ClientConverterService;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Service;

import java.util.*;

import static com.sampleAuth.server.enums.UserEnum.findByAbbr;

@Service
public class ClientConverterServiceImpl implements ClientConverterService {
   @Override
    public Clients convertFromCustom2Client(CustomClient client) {
        Clients trueClient = new Clients();
        trueClient.setClientSecret(client.getClientSecret());
        trueClient.setClientId(client.getClientId());
        trueClient.setAuthorities( new ArrayList<>(findByAbbr(client.getAuthorities()).getGrantedAuthoritySet()));
        trueClient.setScope(new HashSet<>(Arrays.asList("read", "write")));
        trueClient.setAuthorizedGrantTypes(new HashSet<>(Arrays.asList("password", "refresh_token")));
        trueClient.setAdditionalInformation(null);
        trueClient.setAutoApproveScopes(new HashSet<>(Arrays.asList("read", "write")));
        trueClient.setRegisteredRedirectUris(null);
        trueClient.setRefreshTokenValiditySeconds(100);
        trueClient.setAccessTokenValiditySeconds(50);
        trueClient.setResourceIds(null);
        return trueClient;
    }

    @Override
    public Boolean convertFromClient2Custom(Clients client) {
        return null;
    }
}
