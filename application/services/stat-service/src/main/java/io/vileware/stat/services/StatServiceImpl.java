/*
 * Copyright (c) Vileware.  All rights reserved.  http://www.vileware.io
 * The software in this package is published under the terms of the Apache License 2.0
 * license, a copy of which has been included with this distribution in the
 * LICENSE file.
 */
package io.vileware.stat.services;

import io.vileware.common.api.ApiResponse;
import io.vileware.common.dto.StatDTO;
import io.vileware.common.exceptions.InvalidInputException;
import io.vileware.common.exceptions.NotFoundException;
import io.vileware.stat.model.Stat;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class StatServiceImpl implements StatService {

    private static final Logger LOG = LoggerFactory.getLogger(StatServiceImpl.class);

    @Override
    public ResponseEntity<ApiResponse<StatDTO>> getStat(int statId) {
        if (statId < 1) {
            throw new InvalidInputException("Invalid statId: " + statId);
        }

        if (statId != 666) {
            throw new NotFoundException("No stat found for statId: " + statId);
        }


        return new ResponseEntity<>(new ApiResponse<>("Successful retrieval of stat", new Stat(666, "Dummy Stat Label").toDTO()), HttpStatus.OK);
    }
}
