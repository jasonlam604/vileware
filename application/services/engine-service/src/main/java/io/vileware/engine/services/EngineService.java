/*
 * Copyright (c) Vileware.  All rights reserved.  http://www.vileware.io
 * The software in this package is published under the terms of the Apache License 2.0
 * license, a copy of which has been included with this distribution in the
 * LICENSE file.
 */
package io.vileware.engine.services;

import io.swagger.v3.oas.annotations.tags.Tag;
import io.vileware.common.dto.EventDTO;
import io.vileware.common.dto.StatDTO;
import io.vileware.common.dto.StudioDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@Tag(name = "Vileware", description = "REST API for Vileware Services")

/**
 * Engine Services is aggregator for other services and is the service to be exposed
 * for external consumption this follows the Microservices Aggregator Pattern
 */
public interface EngineService {

    @GetMapping(
            value = "/studios/{studioId}",
            produces = "application/json")
    ResponseEntity<io.vileware.common.api.ApiResponse<StudioDTO>> getStudio(@PathVariable int studioId);

    @GetMapping(
            value = "/studios",
            produces = "application/json")
    ResponseEntity<io.vileware.common.api.ApiResponse<List>> getStudios();


    @GetMapping(
            value = "/stats/{statId}",
            produces = "application/json")
    ResponseEntity<io.vileware.common.api.ApiResponse<StatDTO>> getStat(@PathVariable int statId);

    @GetMapping(
            value = "/events/{eventId}",
            produces = "application/json")
    ResponseEntity<io.vileware.common.api.ApiResponse<EventDTO>> getEvent(@PathVariable int eventId);
}
