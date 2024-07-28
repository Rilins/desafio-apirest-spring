package com.benchplayer.domain.controller.dto;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import com.benchplayer.domain.model.User;

public record UserDto(Long id, String name, AccountDto account, List<GameDto> games) {
    
    public UserDto(User model){
        this(
            model.getId(),
            model.getName(),
            Optional.ofNullable(model.getAccount()).map(AccountDto::new).orElse(null),
            Optional.ofNullable(model.getGames()).orElse(List.of()).stream().map(GameDto::new).collect(Collectors.toList())
            );

    }

    public User toModel() {
        User model = new User();
        model.setId(id);
        model.setName(name);
        model.setAccount(Optional.ofNullable(this.account).map(AccountDto::toModel).orElse(null));
        model.setGames(Optional.ofNullable(this.games).orElse(List.of()).stream().map(GameDto::toModel).collect(Collectors.toList()));
        return model;
    }

    
}
