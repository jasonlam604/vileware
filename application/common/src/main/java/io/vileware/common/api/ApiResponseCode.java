/*
 * Copyright (c) Vileware.  All rights reserved.  http://www.vileware.io
 * The software in this package is published under the terms of the Apache License 2.0
 * license, a copy of which has been included with this distribution in the
 * LICENSE file.
 */
package io.vileware.common.api;

import org.springframework.http.HttpStatus;

import java.util.HashMap;
import java.util.Map;

/**
 * This in part will mirror the Standard HTTP codes, values under 1000, above 1000 are specific to Vileware
 * Why NOT just HTTP Status Codes. Outside of readable string messsages from Machine-to-Machine processing
 * it allows for specific handling of specific application errors.
 */
public enum ApiResponseCode {

    // HTTP Status Mirrored Codes
    SUCCESS(HttpStatus.OK.value()),
    BAD_REQUEST(HttpStatus.BAD_REQUEST.value()),

    UNAUTHORIZED(HttpStatus.FORBIDDEN.value()),
    INVALID_INPUT(HttpStatus.UNPROCESSABLE_ENTITY.value()),
    NOT_FOUND(HttpStatus.NOT_FOUND.value()),
    INTERNAL_SERVER_ERROR(HttpStatus.INTERNAL_SERVER_ERROR.value());

    // Vileware Status Codes start at 1000 and above
    // yet to be defined

    public final int value;
    private static Map<Object, Object> map = new HashMap<>();

    private ApiResponseCode(int value) {

        this.value = value;
    }

    static {
        for (ApiResponseCode pageType : ApiResponseCode.values()) {
            map.put(pageType.value, pageType);
        }
    }

    public static ApiResponseCode valueOf(int pageType) {
        return (ApiResponseCode) map.get(pageType);
    }

    public int getValue() {
        return value;
    }
}
