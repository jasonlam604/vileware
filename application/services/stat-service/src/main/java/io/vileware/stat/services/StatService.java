/*
 * Copyright (c) Vileware.  All rights reserved.  http://www.vileware.io
 * The software in this package is published under the terms of the Apache License 2.0
 * license, a copy of which has been included with this distribution in the
 * LICENSE file.
 */
package io.vileware.stat.services;

import io.vileware.common.api.ApiResponse;
import io.vileware.common.dto.StatDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

public interface StatService {

    @GetMapping(
            value = "/stats/{statId}",
            produces = "application/json"
    )
    ResponseEntity<ApiResponse<StatDTO>> getStat(@PathVariable int statId);
}
