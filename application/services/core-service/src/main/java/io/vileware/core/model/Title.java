/*
 * Copyright (c) Vileware.  All rights reserved.  http://www.vileware.io
 * The software in this package is published under the terms of the Apache License 2.0
 * license, a copy of which has been included with this distribution in the
 * LICENSE file.
 */
package io.vileware.core.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.vileware.common.dto.TitleDTO;
import lombok.*;

@Data
@RequiredArgsConstructor
@NoArgsConstructor
public class Title {
    @NonNull
    private Integer id;
    @NonNull
    private Integer studioId;
    @NonNull
    private String name;
    @NonNull
    private Genre genre;

    @JsonIgnore
    private ObjectMapper objectMapper = new ObjectMapper();

    @SneakyThrows
    public TitleDTO toDTO() {
        return this.objectMapper.readValue(this.objectMapper.writeValueAsString(this), TitleDTO.class);
    }

}
