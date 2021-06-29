package com.sampleAuth.server.service;

import com.sampleAuth.server.models.Clients;
import com.sampleAuth.server.models.CustomClient;

public interface ClientConverterService {
    Clients convertFromCustom2Client(CustomClient client);
    Boolean convertFromClient2Custom(Clients client);
}
