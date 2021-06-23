package com.sampleAuth.server.service;

import com.sampleAuth.server.models.User;
import com.sampleAuth.server.repository.UserRepository;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.oauth2.provider.ClientDetails;
import org.springframework.security.oauth2.provider.ClientDetailsService;
import org.springframework.security.oauth2.provider.ClientRegistrationException;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Optional;

@Service
public class ClientRegistrationService implements ClientDetailsService {


    private final String MESSAGE = "The username or password you've entered is incorrect";
    private final UserRepository userRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    public ClientRegistrationService(final UserRepository userRepository,
                                     final BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.userRepository = userRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    public Boolean checkByUserUniqueness(User user) {
        return !userRepository.findByEmail(user.getEmail()).isPresent()
                && !userRepository.findByUserName(user.getUsername()).isPresent();
    }

    public User saveUser(User user){
        user.setPassWord(bCryptPasswordEncoder.encode(user.getPassword()));
        user.setDateCreated(new Date());
        userRepository.save(user);
        return userRepository.findByEmail(user.getEmail()).get();
    }

    @Override
    public ClientDetails loadClientByClientId(String clientId) throws ClientRegistrationException {
        return null;
    }
}
