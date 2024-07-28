package com.benchplayer.domain.controller.dto;

import static java.util.Collections.emptyList;
import static java.util.Optional.ofNullable;
import static java.util.stream.Collectors.toList;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import com.benchplayer.domain.model.User;

public record UserDto(Long id, String name, AccountDto account, List<GameDto> games) {
    
    public UserDto(User model){
        this(
            model.getId(),
            model.getName(),
            ofNullable(model.getAccount()).map(AccountDto::new).orElse(null),
            ofNullable(model.getGames()).orElse(emptyList()).stream().map(GameDto::new).collect(toList())
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
