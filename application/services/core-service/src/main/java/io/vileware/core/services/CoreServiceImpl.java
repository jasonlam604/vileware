/*
 * Copyright (c) Vileware.  All rights reserved.  http://www.vileware.io
 * The software in this package is published under the terms of the Apache License 2.0
 * license, a copy of which has been included with this distribution in the
 * LICENSE file.
 */
package io.vileware.core.services;

import io.vileware.common.api.ApiResponse;
import io.vileware.common.dto.PlayerDTO;
import io.vileware.common.dto.StudioDTO;
import io.vileware.common.dto.TitleDTO;
import io.vileware.common.exceptions.InvalidInputException;
import io.vileware.common.exceptions.NotFoundException;
import io.vileware.core.model.Genre;
import io.vileware.core.model.Player;
import io.vileware.core.model.Studio;
import io.vileware.core.model.Title;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Temporary One Controller to serve all endpoints
 */
@RestController
public class CoreServiceImpl implements StudioService, TitleService, PlayerService {

    private static final Logger LOG = LoggerFactory.getLogger(CoreServiceImpl.class);

    private List<Studio> studios;
    private List<Title> titles;

    @PostConstruct
    public void init() {

        // Dummy Data, until there is a persistence store
        this.studios = new ArrayList<Studio>();
        this.studios.add(new Studio(1, "Dark Unikorn Labs"));
        this.studios.add(new Studio(2, "Vileware"));
        this.studios.add(new Studio(3, "Golem Studios"));


        this.titles = new ArrayList<Title>();
        this.titles.add(new Title(100, 1, "Joey's Adventures", Genre.ROLE_PLAYING));
        this.titles.add(new Title(101, 1, "Space Miner", Genre.ACTION_ADVENTURE));
        this.titles.add(new Title(102, 1, "Space the BlackVoid", Genre.ROLE_PLAYING));
        this.titles.add(new Title(103, 1, "Space Miner II", Genre.ACTION_ADVENTURE));
        this.titles.add(new Title(104, 1, "Joey's Revenge", Genre.ROLE_PLAYING));

        this.titles.add(new Title(200, 2, "Escape from Vile Town", Genre.SHOOTERS));
        this.titles.add(new Title(201, 2, "Vile II, the Battle Zone", Genre.MULTIPLAYER_ONLINE_BATTLE_ARENA));

        this.titles.add(new Title(300, 3, "Dodgeball Extreme", Genre.SPORTS));
        this.titles.add(new Title(301, 3, "B-Boy Battle", Genre.SIMULATION));
        this.titles.add(new Title(302, 3, "Ultimate Frisbee, World Championship", Genre.SPORTS));

    }

    @Override
    public ResponseEntity<ApiResponse<List<StudioDTO>>> getStudios() {
        return new ResponseEntity<>(new ApiResponse<>("Successful retrieval of studios", this.studios.stream().map(Studio::toDTO).collect(Collectors.toList())), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<ApiResponse<StudioDTO>> getStudio(int studioId) {

        if (studioId < 1) {
            throw new InvalidInputException("Invalid studioId: " + studioId);
        }

        Studio aStudio = this.studios.stream()
                .filter(studio -> studioId == studio.getId())
                .findAny()
                .orElseThrow(
                        () -> new NotFoundException("No studio found for studioId: " + studioId)
                );
        return new ResponseEntity<>(new ApiResponse<>("Successful retrieval of studio", aStudio.toDTO()), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<ApiResponse<List<TitleDTO>>> getTitlesByStudio(int studioId) {

        if (studioId < 1) {
            throw new InvalidInputException("Invalid studioId: " + studioId);
        }

        List<Title> titles = this.titles.stream()
                .filter(title -> this.studios.stream()
                        .anyMatch(studio ->
                                studio.getId() == studioId) &&
                        title.getStudioId() == studioId)
                .collect(Collectors.collectingAndThen(Collectors.toList(), result -> {
                    if (result.isEmpty())
                        throw new NotFoundException("No titles found for studio with studioId: " + studioId);
                    return result;
                }));

        return new ResponseEntity<>(new ApiResponse<>("Successful retrieval of studios", titles.stream().map(Title::toDTO).collect(Collectors.toList())), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<ApiResponse<List<TitleDTO>>> getTitles() {
        return new ResponseEntity<>(new ApiResponse<>("Successful retrieval of studios", titles.stream().map(Title::toDTO).collect(Collectors.toList())), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<ApiResponse<TitleDTO>> getTitle(int titleId) {

        if (titleId < 1) {
            throw new InvalidInputException("Invalid titleId: " + titleId);
        }

        Title aTitle = this.titles.stream()
                .filter(title -> titleId == title.getId())
                .findAny()
                .orElseThrow(
                        () -> new NotFoundException("No title found for titleId: " + titleId)
                );
        return new ResponseEntity<>(new ApiResponse<>("Successful retrieval of title", aTitle.toDTO()), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<ApiResponse<PlayerDTO>> getPlayer(int playerId) {
        if (playerId < 1) {
            throw new InvalidInputException("Invalid playerId: " + playerId);
        }

        if (playerId != 77) {
            throw new NotFoundException("No stat found for playerId: " + playerId);
        }

        return new ResponseEntity<>(new ApiResponse<>("Successful retrieval of player", new Player(77, "eyekonn").toDTO()), HttpStatus.OK);
    }
}
