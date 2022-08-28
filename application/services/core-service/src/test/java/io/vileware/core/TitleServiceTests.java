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
class TitleServiceTests {

    @Autowired
    private WebTestClient client;


    @Test
    void getTitles() {
        client.get()
                .uri("/titles")
                .accept(APPLICATION_JSON)
                .exchange()
                .expectStatus().isOk()
                .expectHeader().contentType(APPLICATION_JSON)
                .expectBody()
                .jsonPath("$.data.length()").isEqualTo(10)
                .jsonPath("$.data[0].id").isEqualTo(100)
                .jsonPath("$.data[0].name").isEqualTo("Joey's Adventures")
                .jsonPath("$.data[0].genre").isEqualTo("ROLE_PLAYING");
    }

    @Test
    void getTitlesByStudioId() {
        int studioId = 2;

        client.get()
                .uri("/studios/" + studioId + "/titles")
                .accept(APPLICATION_JSON)
                .exchange()
                .expectStatus().isOk()
                .expectHeader().contentType(APPLICATION_JSON)
                .expectBody()
                .jsonPath("$.length()").isEqualTo(4)
                .jsonPath("$.data[0].id").isEqualTo(200)
                .jsonPath("$.data[0].name").isEqualTo("Escape from Vile Town")
                .jsonPath("$.data[0].genre").isEqualTo("SHOOTERS")
                .jsonPath("$.data[1].id").isEqualTo(201)
                .jsonPath("$.data[1].name").isEqualTo("Vile II, the Battle Zone")
                .jsonPath("$.data[1].genre").isEqualTo("MULTIPLAYER_ONLINE_BATTLE_ARENA");

    }

    @Test
    void getTitleByTitleId() {
        int titleId = 200;
        client.get()
                .uri("/titles/" + titleId)
                .accept(APPLICATION_JSON)
                .exchange()
                .expectStatus().isOk()
                .expectHeader().contentType(APPLICATION_JSON)
                .expectBody()
                .jsonPath("$.data.id").isEqualTo(titleId);
    }

    @Test
    void getTitleNotFound() {
        int titleId = 999;
        client.get()
                .uri("/titles/" + titleId)
                .accept(APPLICATION_JSON)
                .exchange()
                .expectStatus().isNotFound()
                .expectHeader().contentType(APPLICATION_JSON)
                .expectBody()
                .jsonPath("$.message").isEqualTo("No title found for titleId: " + titleId);
    }


    @Test
    void getTitleInvalidParameterString() {
        client.get()
                .uri("/titles/no-integer")
                .accept(APPLICATION_JSON)
                .exchange()
                .expectStatus().isEqualTo(UNPROCESSABLE_ENTITY)
                .expectHeader().contentType(APPLICATION_JSON)
                .expectBody()
                .jsonPath("$.message").isEqualTo("Invalid number format");
    }

}
