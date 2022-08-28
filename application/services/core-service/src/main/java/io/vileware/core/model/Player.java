/*
 * Copyright (c) Vileware.  All rights reserved.  http://www.vileware.io
 * The software in this package is published under the terms of the Apache License 2.0
 * license, a copy of which has been included with this distribution in the
 * LICENSE file.
 */
package io.vileware.core.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.vileware.common.domain.Base;
import io.vileware.common.dto.PlayerDTO;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.SneakyThrows;

@Data
@NoArgsConstructor
public class Player extends Base {
    @NonNull
    private Integer id;

    @NonNull
    private String username;

    @JsonIgnore
    private String password;

    @JsonIgnore
    private PlayerProfile playerProfile;

    public Player(int id, String username) {
        this.id = id;
        this.username = username;
    }

    public Player(int id, String username, PlayerProfile playerProfile) {
        this.id = id;
        this.playerProfile = playerProfile;
    }

    @SneakyThrows
    public PlayerDTO toDTO() {
        return this.toDTO(this, PlayerDTO.class);
    }

}
