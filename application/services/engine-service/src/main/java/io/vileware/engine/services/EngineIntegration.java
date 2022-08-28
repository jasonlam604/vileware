/*
 * Copyright (c) Vileware.  All rights reserved.  http://www.vileware.io
 * The software in this package is published under the terms of the Apache License 2.0
 * license, a copy of which has been included with this distribution in the
 * LICENSE file.
 */
package io.vileware.engine.services;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import io.vileware.common.dto.EventDTO;
import io.vileware.common.dto.StatDTO;
import io.vileware.common.dto.StudioDTO;
import io.vileware.common.exceptions.InternalServerException;
import io.vileware.common.exceptions.InvalidInputException;
import io.vileware.common.exceptions.NotFoundException;
import io.vileware.common.http.HttpErrorInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
public class EngineIntegration {
    private static final Logger LOG = LoggerFactory.getLogger(EngineIntegration.class);
    private final RestTemplate restTemplate;
    private final ObjectMapper objectMapper;
    private final String baseEventServiceURL;
    private final String baseStudioServiceURL;

    private final String baseStatServiceURL;

    @Autowired
    public EngineIntegration(
            RestTemplate restTemplate,
            ObjectMapper mapper,
            @Value("${app.core-service.host}") String studioServiceHost,
            @Value("${app.core-service.port}") int studioServicePort,
            @Value("${app.event-service.host}") String eventServiceHost,
            @Value("${app.event-service.port}") int eventServicePort,
            @Value("${app.stat-service.host}") String statServiceHost,
            @Value("${app.stat-service.port}") int statServicePort) {

        this.restTemplate = restTemplate;
        this.objectMapper = mapper;

        this.baseEventServiceURL = "http://" + eventServiceHost + ":" + eventServicePort;
        this.baseStudioServiceURL = "http://" + studioServiceHost + ":" + studioServicePort;
        this.baseStatServiceURL = "http://" + statServiceHost + ":" + statServicePort;
    }

    public List<StudioDTO> getStudios() {
        List<StudioDTO> studioDTOS = new ArrayList<>();
        try {
            Optional<JsonArray> dataResponse = this.parseResponseDataList(this.baseStudioServiceURL + "/studios");
            if (dataResponse.isPresent()) {
                studioDTOS = this.objectMapper.readValue(dataResponse.get().toString(), new TypeReference<List<StudioDTO>>() {
                });
            }
        } catch (HttpClientErrorException ex) {
            this.processException(ex);
        } catch (Exception e) {
            throw new InternalServerException(e);
        }
        return studioDTOS;
    }

    public StudioDTO getStudio(int studioId) {
        StudioDTO studioDTO = null;
        try {
            Optional<JsonObject> dataResponse = this.parseResponseData(baseStudioServiceURL + "/studios/" + studioId);
            if (dataResponse.isPresent()) {
                studioDTO = this.objectMapper.readValue(dataResponse.get().toString(), StudioDTO.class);
            }
            return studioDTO;
        } catch (HttpClientErrorException ex) {
            this.processException(ex);
        } catch (Exception e) {
            throw new InternalServerException(e);
        }
        return studioDTO;
    }

    public StatDTO getStat(int statId) {
        StatDTO statDTO = null;
        try {
            Optional<JsonObject> dataResponse = this.parseResponseData(baseStatServiceURL + "/stats/" + statId);
            if (dataResponse.isPresent()) {
                statDTO = this.objectMapper.readValue(dataResponse.get().toString(), StatDTO.class);
            }
            return statDTO;
        } catch (HttpClientErrorException ex) {
            this.processException(ex);
        } catch (Exception e) {
            throw new InternalServerException(e);
        }
        return statDTO;
    }

    public EventDTO getEvent(int eventId) {
        EventDTO eventDTO = null;
        try {
            Optional<JsonObject> dataResponse = this.parseResponseData(baseEventServiceURL + "/events/" + eventId);
            if (dataResponse.isPresent()) {
                eventDTO = this.objectMapper.readValue(dataResponse.get().toString(), EventDTO.class);
            }
            return eventDTO;
        } catch (HttpClientErrorException ex) {
            this.processException(ex);
        } catch (Exception e) {
            throw new InternalServerException(e);
        }
        return eventDTO;
    }

    private void processException(HttpClientErrorException ex) {

        switch (ex.getStatusCode()) {

            case NOT_FOUND:
                throw new NotFoundException(getErrorMessage(ex));

            case UNPROCESSABLE_ENTITY:
                throw new InvalidInputException(getErrorMessage(ex));

            default:
                LOG.warn("Got an unexpected HTTP error: {}, will rethrow it", ex.getStatusCode());
                LOG.warn("Error body: {}", ex.getResponseBodyAsString());
                throw ex;
        }
    }

    private String getErrorMessage(HttpClientErrorException ex) {
        try {
            return objectMapper.readValue(ex.getResponseBodyAsString(), HttpErrorInfo.class).getMessage();
        } catch (IOException ioex) {
            return ex.getMessage();
        }
    }

    private Optional<JsonArray> parseResponseDataList(String url) {
        ResponseEntity<String> response = this.restTemplate.getForEntity(url, String.class);
        if (!response.getBody().isEmpty()) {
            JsonObject jsonObject = JsonParser.parseString(response.getBody()).getAsJsonObject();
            return Optional.ofNullable(jsonObject.get("data").getAsJsonArray());
        }
        return Optional.empty();
    }

    private Optional<JsonObject> parseResponseData(String url) {
        ResponseEntity<String> response = this.restTemplate.getForEntity(url, String.class);
        if (!response.getBody().isEmpty()) {
            JsonObject jsonObject = JsonParser.parseString(response.getBody()).getAsJsonObject();
            return Optional.ofNullable(jsonObject.get("data").getAsJsonObject());
        }
        return Optional.empty();
    }

}
