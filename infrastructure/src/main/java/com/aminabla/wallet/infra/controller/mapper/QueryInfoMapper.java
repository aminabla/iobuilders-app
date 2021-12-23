package com.aminabla.wallet.infra.controller.mapper;

import com.aminabla.wallet.domain.Balance;
import com.aminabla.wallet.domain.WalletOperation;
import com.aminabla.wallet.infra.controller.dto.output.WalletBalanceDto;
import com.aminabla.wallet.infra.controller.dto.output.WalletOperationDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface QueryInfoMapper {

    public static final QueryInfoMapper INSTANCE = Mappers.getMapper(QueryInfoMapper.class);

    @Mapping(target = "walletId", source = "id.walletAlias")
    @Mapping(target = "amount", source = "amount.amount")
    WalletBalanceDto toOutput(Balance balance);

    @Mapping(target = "operationId", source = "id.identifier")
    @Mapping(target = "walletId", source = "walletId.walletAlias")
    @Mapping(target = "amount", source = "amount.amount")
    WalletOperationDto toOutput(WalletOperation walletOperation);

    List<WalletOperationDto> toOutput(List<WalletOperation> walletOperation);
}
