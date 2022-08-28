/*
 * Copyright (c) Vileware.  All rights reserved.  http://www.vileware.io
 * The software in this package is published under the terms of the Apache License 2.0
 * license, a copy of which has been included with this distribution in the
 * LICENSE file.
 */
package io.vileware.engine;

import io.vileware.common.dto.StudioDTO;
import io.vileware.common.exceptions.NotFoundException;
import io.vileware.engine.services.EngineIntegration;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.reactive.server.WebTestClient;

import static org.mockito.Mockito.when;
import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT;
import static org.springframework.http.MediaType.APPLICATION_JSON;

@SpringBootTest(webEnvironment = RANDOM_PORT)
class EngineServiceApplicationTests {

    private static final int STUDIO_ID_VALID = 1;
    private static final int STUDIO_ID_NOT_FOUND = 99;

    private static final String STUDIO_NAME_VALID = "mock-studio-name";

    @Autowired
    private WebTestClient client;

    @MockBean
    private EngineIntegration engineIntegration;

    @Test
    void contextLoads() {
    }


    @BeforeEach
    void setUp() {
        when(engineIntegration.getStudio(STUDIO_ID_VALID))
                .thenReturn(new StudioDTO(STUDIO_ID_VALID, STUDIO_NAME_VALID));
        when(engineIntegration.getStudio(STUDIO_ID_NOT_FOUND))
                .thenThrow(new NotFoundException("No studio found for studioId: " + STUDIO_ID_NOT_FOUND));
    }

    @Test
    void getStudioById() {
        client.get()
                .uri("/studios/" + STUDIO_ID_VALID)
                .accept(APPLICATION_JSON)
                .exchange()
                .expectStatus().isOk()
                .expectHeader().contentType(APPLICATION_JSON)
                .expectBody()
                .jsonPath("$.data.id").isEqualTo(STUDIO_ID_VALID)
                .jsonPath("$.data.name").isEqualTo(STUDIO_NAME_VALID);
    }


    @Test
    void getStudioßNotFound() {
        client.get()
                .uri("/studios/" + STUDIO_ID_NOT_FOUND)
                .accept(APPLICATION_JSON)
                .exchange()
                .expectStatus().isNotFound()
                .expectHeader().contentType(APPLICATION_JSON)
                .expectBody()
                .jsonPath("$.message").isEqualTo("No studio found for studioId: " + STUDIO_ID_NOT_FOUND)
                .jsonPath("$.code").isEqualTo(404)
                .jsonPath("$.data").isEqualTo(null);
    }

}
