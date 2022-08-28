/*
 * Copyright (c) Vileware.  All rights reserved.  http://www.vileware.io
 * The software in this package is published under the terms of the Apache License 2.0
 * license, a copy of which has been included with this distribution in the
 * LICENSE file.
 */
package io.vileware.engine.services;

import io.vileware.common.api.ApiResponse;
import io.vileware.common.dto.EventDTO;
import io.vileware.common.dto.StatDTO;
import io.vileware.common.dto.StudioDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RestController
public class EngineServiceImpl implements EngineService {

    private EngineIntegration integration;

    @Autowired
    public EngineServiceImpl(EngineIntegration integration) {
        this.integration = integration;
    }

    @Override
    public ResponseEntity<ApiResponse<StudioDTO>> getStudio(int studioId) {
        return new ResponseEntity<>(new ApiResponse<>("Successful retrieval of studio", integration.getStudio(studioId)), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<ApiResponse<List>> getStudios() {
        return new ResponseEntity<>(new ApiResponse<>("Successful retrieval of studios", integration.getStudios()), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<ApiResponse<StatDTO>> getStat(int statId) {
        return new ResponseEntity<>(new ApiResponse<>("Successful retrieval of stat", integration.getStat(statId)), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<ApiResponse<EventDTO>> getEvent(int eventId) {
        return new ResponseEntity<>(new ApiResponse<>("Successful retrieval of event", integration.getEvent(eventId)), HttpStatus.OK);
    }
}
