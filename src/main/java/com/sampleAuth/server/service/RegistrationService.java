package com.sampleAuth.server.service;

import com.sampleAuth.server.models.User;

public interface RegistrationService {
    Boolean register(User user);
    Boolean update(User user);
    Boolean delete(String userName);
}
