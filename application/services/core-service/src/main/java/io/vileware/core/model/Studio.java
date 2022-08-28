/*
 * Copyright (c) Vileware.  All rights reserved.  http://www.vileware.io
 * The software in this package is published under the terms of the Apache License 2.0
 * license, a copy of which has been included with this distribution in the
 * LICENSE file.
 */
package io.vileware.core.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.vileware.common.domain.Base;
import io.vileware.common.dto.StudioDTO;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.SneakyThrows;

@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class Studio extends Base {

    private int id;
    private String name;

    @JsonIgnore
    private ObjectMapper objectMapper;

    public Studio(int id, String name) {
        this.id = id;
        this.name = name;
    }

    @SneakyThrows
    public StudioDTO toDTO() {
        return this.toDTO(this, StudioDTO.class);
    }

}
