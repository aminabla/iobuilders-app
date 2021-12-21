package com.aminabla.wallet.domain;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Value;

@RequiredArgsConstructor
public class User {

    @Getter
    private final UserId userId;

    @Value
    public static class UserId implements Identifier<String>{
        String identifier;
    }
}
