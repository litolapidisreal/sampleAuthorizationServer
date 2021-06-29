package com.sampleAuth.server.service;

import com.sampleAuth.server.models.CustomClient;
import com.sampleAuth.server.models.User;
import com.sampleAuth.server.repository.ClientRepository;
import com.sampleAuth.server.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.oauth2.common.exceptions.InvalidTokenException;
import org.springframework.security.oauth2.provider.ClientDetails;
import org.springframework.security.oauth2.provider.ClientDetailsService;
import org.springframework.security.oauth2.provider.ClientRegistrationException;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Optional;

@Service
public class ClientRegistrationService implements ClientDetailsService {
    @Autowired
    ClientRepository clientRepository;
    @Autowired
    ClientConverterService clientConverterService;

    private final String MESSAGE = "The username or password you've entered is incorrect";

    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    public ClientRegistrationService(final BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    public Boolean checkByUserUniqueness(CustomClient client) {
        return !clientRepository.findByClientId(client.getClientId()).isPresent();
    }

    public CustomClient saveUser(CustomClient client){
        client.setClientSecret(bCryptPasswordEncoder.encode(client.getClientSecret()));
        clientRepository.save(client);
        return client;
    }

    public CustomClient sampleClient(){
        CustomClient client = new CustomClient();
        client.setClientId("SAMPLE_01");
        client.setClientSecret(bCryptPasswordEncoder.encode("hard_password"));
        client.setAuthorities("ADMIN");
        clientRepository.save(client);
        return client;
    }

    @Override
    public ClientDetails loadClientByClientId(String clientId) throws ClientRegistrationException {
        return clientConverterService.convertFromCustom2Client(
                clientRepository.findByClientId(clientId)
                        .orElseThrow(() -> new ClientRegistrationException(
                                        "Invalid Token! Please double check code!")));
    }
}
