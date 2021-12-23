package com.aminabla.wallet.infra.controller.impl;


import com.aminabla.wallet.application.buses.QueryBus;
import com.aminabla.wallet.application.commands.impl.QueryWalletBalanceCommand;
import com.aminabla.wallet.application.commands.impl.QueryWalletHistoryCommand;
import com.aminabla.wallet.domain.Wallet.WalletId;
import com.aminabla.wallet.infra.controller.WalletInfoController;
import com.aminabla.wallet.infra.controller.auth.AuthenticationAccessHelper;
import com.aminabla.wallet.infra.controller.dto.output.WalletBalanceDto;
import com.aminabla.wallet.infra.controller.dto.output.WalletOperationDto;
import com.aminabla.wallet.infra.controller.mapper.QueryInfoMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@Slf4j
class WalletInfoControllerImpl implements WalletInfoController {

	private final QueryBus queryBus;

	private final AuthenticationAccessHelper authenticationHelper;

	public WalletInfoControllerImpl(QueryBus queryBus, AuthenticationAccessHelper securityHolder) {
		this.queryBus = queryBus;
		this.authenticationHelper = securityHolder;
	}

	@Override
	public WalletBalanceDto getBalance(String walletAlias) {
		QueryWalletBalanceCommand command = new QueryWalletBalanceCommand(new WalletId(walletAlias, authenticationHelper.getAuthentication().getName()));
		log.debug("Request for balance of wallet: '{}'", command.getWalletId());
		return QueryInfoMapper.INSTANCE.toOutput(queryBus.handle(command));
	}


	@Override
	public List<WalletOperationDto> getHistory(String walletAlias) {
		QueryWalletHistoryCommand command = new QueryWalletHistoryCommand(new WalletId(walletAlias, authenticationHelper.getAuthentication().getName()));
		log.debug("Request for operations history of wallet : '{}' ", command.getWalletId());
		return QueryInfoMapper.INSTANCE.toOutput(queryBus.handle(command));
	}
}
