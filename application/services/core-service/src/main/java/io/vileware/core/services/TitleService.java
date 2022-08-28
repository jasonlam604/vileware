/*
 * Copyright (c) Vileware.  All rights reserved.  http://www.vileware.io
 * The software in this package is published under the terms of the Apache License 2.0
 * license, a copy of which has been included with this distribution in the
 * LICENSE file.
 */
package io.vileware.core.services;

import io.vileware.common.api.ApiResponse;
import io.vileware.common.dto.TitleDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

public interface TitleService {

    @GetMapping(
            value = "/studios/{studioId}/titles",
            produces = "application/json")
    ResponseEntity<ApiResponse<List<TitleDTO>>> getTitlesByStudio(@PathVariable int studioId);

    @GetMapping(
            value = "/titles",
            produces = "application/json")
    ResponseEntity<ApiResponse<List<TitleDTO>>> getTitles();

    @GetMapping(
            value = "/titles/{titleId}",
            produces = "application/json")
    ResponseEntity<ApiResponse<TitleDTO>> getTitle(@PathVariable int titleId);
}
