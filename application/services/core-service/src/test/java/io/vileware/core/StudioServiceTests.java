/*
 * Copyright (c) Vileware.  All rights reserved.  http://www.vileware.io
 * The software in this package is published under the terms of the Apache License 2.0
 * license, a copy of which has been included with this distribution in the
 * LICENSE file.
 */
package io.vileware.core;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.reactive.server.WebTestClient;

import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT;
import static org.springframework.http.HttpStatus.UNPROCESSABLE_ENTITY;
import static org.springframework.http.MediaType.APPLICATION_JSON;

@SpringBootTest(webEnvironment = RANDOM_PORT)
class StudioServiceTests {

    @Autowired
    private WebTestClient client;

    @Test
    void getStudios() {
        client.get()
                .uri("/studios")
                .accept(APPLICATION_JSON)
                .exchange()
                .expectStatus().isOk()
                .expectHeader().contentType(APPLICATION_JSON)
                .expectBody()
                .jsonPath("$.data.length()").isEqualTo(3)
                .jsonPath("$.data.[0].id").isEqualTo(1)
                .jsonPath("$.data.[0].name").isEqualTo("Dark Unikorn Labs")
                .jsonPath("$.data.[1].id").isEqualTo(2)
                .jsonPath("$.data.[1].name").isEqualTo("Vileware");
    }

    @Test
    void getStudioByStudioId() {
        int studioId = 1;
        client.get()
                .uri("/studios/" + studioId)
                .accept(APPLICATION_JSON)
                .exchange()
                .expectStatus().isOk()
                .expectHeader().contentType(APPLICATION_JSON)
                .expectBody()
                .jsonPath("$.data.id").isEqualTo(studioId);
    }


    @Test
    void getStudioNotFound() {
        int studioId = 99;
        client.get()
                .uri("/studios/" + studioId)
                .accept(APPLICATION_JSON)
                .exchange()
                .expectStatus().isNotFound()
                .expectHeader().contentType(APPLICATION_JSON)
                .expectBody()
                .jsonPath("$.message").isEqualTo("No studio found for studioId: " + studioId);
    }

    @Test
    void getStudioInvalidParameterString() {
        client.get()
                .uri("/studios/no-integer")
                .accept(APPLICATION_JSON)
                .exchange()
                .expectStatus().isEqualTo(UNPROCESSABLE_ENTITY)
                .expectHeader().contentType(APPLICATION_JSON)
                .expectBody()
                .jsonPath("$.message").isEqualTo("Invalid number format");
    }
}
