package com.benchplayer.domain.controller.dto;

import com.benchplayer.domain.model.Account;

public record AccountDto(Long id, String number, String accountType) {
    
    public AccountDto(Account model) {
        this(model.getId(), model.getNumber(), model.getAccountType());
    }

    public Account toModel() {
        Account model = new Account();
        model.setId(id);
        model.setNumber(number);
        model.setAccountType(accountType);
        return model;
    }
}
