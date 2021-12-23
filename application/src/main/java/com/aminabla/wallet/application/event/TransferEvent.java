package com.aminabla.wallet.application.event;

import com.aminabla.wallet.domain.Money;
import com.aminabla.wallet.domain.Wallet;
import lombok.Value;

@Value
public class TransferEvent implements WalletEvent{

    EventId eventId;

    Wallet.WalletId walletId;

    Money amount;

}
