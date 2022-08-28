/*
 * Copyright (c) Vileware.  All rights reserved.  http://www.vileware.io
 * The software in this package is published under the terms of the Apache License 2.0
 * license, a copy of which has been included with this distribution in the
 * LICENSE file.
 */
package io.vileware.common.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.SneakyThrows;

public class Base {
    @JsonIgnore
    private ObjectMapper objectMapper;

    @SneakyThrows
    protected <T> T toDTO(Object obj, Class<T> cls) {
        if (this.objectMapper == null) {
            this.objectMapper = new ObjectMapper();
        }
        return this.objectMapper.readValue(objectMapper.writeValueAsString(obj), cls);
    }
}
