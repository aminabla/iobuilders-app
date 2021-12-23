package com.aminabla.wallet.infra.controller.dto.output;

import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
public class WalletOperationDto {

    private Long operationId;

    private String walletId;

    private LocalDateTime timestamp;

    private BigDecimal amount;

}
