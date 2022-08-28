/*
 * Copyright (c) Vileware.  All rights reserved.  http://www.vileware.io
 * The software in this package is published under the terms of the Apache License 2.0
 * license, a copy of which has been included with this distribution in the
 * LICENSE file.
 */
package io.vileware.event.model;

import io.vileware.common.domain.Base;
import io.vileware.common.dto.EventDTO;
import lombok.*;

@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class Event extends Base {

    @NonNull
    private Integer id;

    @NonNull
    private String name;

    @NonNull
    private String description;

    public Event(int id, @NonNull String name, @NonNull String description) {
        this.id = id;
        this.name = name;
        this.description = description;
    }

    @SneakyThrows
    public EventDTO toDTO() {
        return this.toDTO(this, EventDTO.class);
    }

}
