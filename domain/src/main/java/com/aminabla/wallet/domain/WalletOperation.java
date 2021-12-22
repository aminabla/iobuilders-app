package com.aminabla.wallet.domain;

import com.aminabla.wallet.domain.User.UserId;
import com.aminabla.wallet.domain.Wallet.WalletId;
import lombok.Getter;
import lombok.NonNull;
import lombok.Value;

import java.time.LocalDateTime;

@Getter
public class WalletOperation {

    private WalletOperationId id;

    @NonNull
    private final WalletId walletId;

    @NonNull
    private final LocalDateTime timestamp;

    @NonNull
    private final Amount amount;


    public WalletOperation(
            WalletOperationId operationId,
            @NonNull WalletId walletId,
            @NonNull LocalDateTime timestamp,
            Amount money) {
        this.id = operationId;
        this.walletId = walletId;
        this.timestamp = timestamp;
        this.amount = money;
    }


    public WalletOperation(
            WalletId targetWalletId,
            LocalDateTime timestamp,
            Amount money) {
        this(null, targetWalletId, timestamp, money);
    }

    public WalletOperation(WalletId id, Amount amount) {
        this(id, LocalDateTime.now(), amount);
    }

    @Value
    public static class WalletOperationId implements Identifier<Long>{
        private final Long identifier;
    }
}
