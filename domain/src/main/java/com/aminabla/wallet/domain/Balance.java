package com.aminabla.wallet.domain;

import com.aminabla.wallet.domain.Wallet.WalletId;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class Balance {

    @Getter
    private final WalletId id;
    @Getter
    private final Money amount;
}
