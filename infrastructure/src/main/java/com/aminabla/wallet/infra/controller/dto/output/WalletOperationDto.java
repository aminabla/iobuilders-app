package com.aminabla.wallet.infra.controller.dto.output;

import com.aminabla.wallet.domain.Amount;
import com.aminabla.wallet.domain.Wallet;
import lombok.Data;
import lombok.Getter;
import lombok.NonNull;
import lombok.Value;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
public class WalletOperationDto {

    private Long operationId;

    private Long sourceWalletId;

    private Long targetWalletId;

    private LocalDateTime timestamp;

    private BigDecimal amount;

}
