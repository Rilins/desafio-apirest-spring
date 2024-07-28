package com.benchplayer.domain.controller.dto;

import com.benchplayer.domain.model.Game;

public record GameDto(Long id, String name, String description) {
    
    public GameDto(Game model) {
        this(model.getId(), model.getName(), model.getDescription());
    }

    public Game toModel() {
        Game model = new Game();
        model.setId(id);
        model.setName(name);
        model.setDescription(description);
        return model;
    }

}
