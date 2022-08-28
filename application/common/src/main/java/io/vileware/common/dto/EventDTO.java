/*
 * Copyright (c) Vileware.  All rights reserved.  http://www.vileware.io
 * The software in this package is published under the terms of the Apache License 2.0
 * license, a copy of which has been included with this distribution in the
 * LICENSE file.
 */
package io.vileware.common.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class EventDTO {
    private Integer id;
    private String name;
    private String description;
}
