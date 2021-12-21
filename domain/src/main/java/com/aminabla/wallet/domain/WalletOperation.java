package com.aminabla.wallet.domain;

import com.aminabla.wallet.domain.Wallet.WalletId;
import lombok.Getter;
import lombok.NonNull;
import lombok.Value;

import java.time.LocalDateTime;

@Getter
public class WalletOperation {

    private WalletOperationId id;

    @NonNull
    private final WalletId sourceWalletId;

    @NonNull
    private final WalletId targetWalletId;

    @NonNull
    private final LocalDateTime timestamp;

    @NonNull
    private final Amount amount;


    public WalletOperation(
            WalletOperationId operationId,
            @NonNull WalletId sourceWalletId,
            @NonNull WalletId targetWalletId,
            @NonNull LocalDateTime timestamp,
            Amount money) {
        this.id = operationId;
        this.sourceWalletId = sourceWalletId;
        this.targetWalletId = targetWalletId;
        this.timestamp = timestamp;
        this.amount = money;
    }


    public WalletOperation(
            WalletId sourceWalletId,
            WalletId targetWalletId,
            LocalDateTime timestamp,
            Amount money) {
        this(null, sourceWalletId, targetWalletId, timestamp, money);
    }

    public WalletOperation(WalletId sourceWalletId, WalletId id, Amount amount) {
        this(sourceWalletId, id, LocalDateTime.now(), amount);
    }

    @Value
    public static class WalletOperationId implements Identifier<Long>{
        private final Long identifier;
    }
}
