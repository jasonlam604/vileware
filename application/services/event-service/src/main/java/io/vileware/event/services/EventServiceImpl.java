/*
 * Copyright (c) Vileware.  All rights reserved.  http://www.vileware.io
 * The software in this package is published under the terms of the Apache License 2.0
 * license, a copy of which has been included with this distribution in the
 * LICENSE file.
 */
package io.vileware.event.services;


import io.vileware.common.api.ApiResponse;
import io.vileware.common.dto.EventDTO;
import io.vileware.common.exceptions.InvalidInputException;
import io.vileware.common.exceptions.NotFoundException;
import io.vileware.event.model.Event;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class EventServiceImpl implements EventService {

    private static final Logger LOG = LoggerFactory.getLogger(EventServiceImpl.class);

    @Override
    public ResponseEntity<ApiResponse<EventDTO>> getEvent(int eventId) {

        if (eventId < 1) {
            throw new InvalidInputException("Invalid eventId: " + eventId);
        }

        if (eventId != 42) {
            throw new NotFoundException("No event found for eventId: " + eventId);
        }

        return new ResponseEntity<>(new ApiResponse<>("Successful retrieval of event", new Event(42, "Dummy Event Label", "A Dummy Event Description").toDTO()), HttpStatus.OK);
    }
}
