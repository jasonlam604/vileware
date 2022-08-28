/*
 * Copyright (c) Vileware.  All rights reserved.  http://www.vileware.io
 * The software in this package is published under the terms of the Apache License 2.0
 * license, a copy of which has been included with this distribution in the
 * LICENSE file.
 */
package io.vileware.stat.model;

import io.vileware.common.domain.Base;
import io.vileware.common.dto.StatDTO;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.lang.NonNull;

@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class Stat extends Base {

    @NonNull
    private Integer id;

    @NonNull
    private String name;

    public Stat(int id, @NonNull String name) {
        this.id = id;
        this.name = name;
    }

    @SneakyThrows
    public StatDTO toDTO() {
        return this.toDTO(this, StatDTO.class);
    }
}
