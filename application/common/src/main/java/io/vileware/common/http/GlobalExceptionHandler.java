/*
 * Copyright (c) Vileware.  All rights reserved.  http://www.vileware.io
 * The software in this package is published under the terms of the Apache License 2.0
 * license, a copy of which has been included with this distribution in the
 * LICENSE file.
 */
package io.vileware.common.http;

import io.vileware.common.api.ApiResponse;
import io.vileware.common.api.ApiResponseCode;
import io.vileware.common.exceptions.InternalServerException;
import io.vileware.common.exceptions.NotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

import static org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR;

@RestControllerAdvice
class GlobalExceptionHandler {

    private static final Logger LOG = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    @ExceptionHandler({InternalServerException.class})
    public ResponseEntity<Object> handleInternalServerExceptions(Exception ex, WebRequest request) {
        return new ResponseEntity<>(new ApiResponse<>("Internal Server Error", null, ApiResponseCode.INTERNAL_SERVER_ERROR), INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler({NumberFormatException.class})
    public ResponseEntity<Object> handleNumberFormatExceptions(Exception ex, WebRequest request) {
        return new ResponseEntity<>(new ApiResponse<>("Invalid number format", null, ApiResponseCode.INVALID_INPUT), HttpStatus.UNPROCESSABLE_ENTITY);
    }

    @ExceptionHandler({NotFoundException.class})
    public ResponseEntity<Object> handleNotFoundExceptions(Exception ex, WebRequest request) {
        return new ResponseEntity<>(new ApiResponse<>(ex.getMessage(), null, ApiResponseCode.NOT_FOUND), HttpStatus.NOT_FOUND);
    }
}
