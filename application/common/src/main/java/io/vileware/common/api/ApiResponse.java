/*
 * Copyright (c) Vileware.  All rights reserved.  http://www.vileware.io
 * The software in this package is published under the terms of the Apache License 2.0
 * license, a copy of which has been included with this distribution in the
 * LICENSE file.
 */
package io.vileware.common.api;

import lombok.Data;
import org.springframework.http.HttpStatus;

import java.io.Serializable;

@Data
public class ApiResponse<T> implements Serializable {

    private static String STATUS_SUCCESS = "success";
    private static String STATUS_FAILURE = "failure";

    private String status;
    private String message;
    private int code;
    private T data;

    public ApiResponse(String message, T data) {
        this.code = ApiResponseCode.SUCCESS.getValue();
        this.status = ApiResponse.STATUS_SUCCESS;
        this.message = message;
        this.data = data;
    }

    public ApiResponse(String message, T data, ApiResponseCode apiResponseCode) {
        this.code = apiResponseCode.getValue();
        if (this.code != HttpStatus.OK.value()) {
            this.status = ApiResponse.STATUS_FAILURE;
        }
        this.message = message;
        this.data = data;
    }
}
