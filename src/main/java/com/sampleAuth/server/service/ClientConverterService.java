package com.sampleAuth.server.service;

import com.sampleAuth.server.models.Clients;
import com.sampleAuth.server.models.CustomClient;

public interface ClientConverterService {
    Boolean convertFromCustom2Client(CustomClient client);
    Boolean convertFromClient2Client(Clients client);
}
