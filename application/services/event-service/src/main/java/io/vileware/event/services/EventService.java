/*
 * Copyright (c) Vileware.  All rights reserved.  http://www.vileware.io
 * The software in this package is published under the terms of the Apache License 2.0
 * license, a copy of which has been included with this distribution in the
 * LICENSE file.
 */
package io.vileware.event.services;

import io.vileware.common.api.ApiResponse;
import io.vileware.common.dto.EventDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

public interface EventService {
    @GetMapping(
            value = "/events/{eventId}",
            produces = "application/json"
    )
    ResponseEntity<ApiResponse<EventDTO>> getEvent(@PathVariable int eventId);
}
