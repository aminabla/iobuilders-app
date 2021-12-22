package com.aminabla.wallet.infra.controller.impl;


import com.aminabla.wallet.application.buses.QueryBus;
import com.aminabla.wallet.application.ports.api.WalletInfo;
import com.aminabla.wallet.application.commands.QueryWalletBalanceCommand;
import com.aminabla.wallet.application.commands.QueryWalletHistoryCommand;
import com.aminabla.wallet.domain.Wallet.WalletId;
import com.aminabla.wallet.infra.controller.WalletInfoController;
import com.aminabla.wallet.infra.controller.dto.output.WalletBalanceDto;
import com.aminabla.wallet.infra.controller.dto.output.WalletOperationDto;
import com.aminabla.wallet.infra.controller.mapper.QueryInfoMapper;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@RestController
class WalletInfoControllerImpl implements WalletInfoController {

	private final QueryBus queryBus;

	public WalletInfoControllerImpl(QueryBus queryBus) {
		this.queryBus = queryBus;
	}


	@Override
	public WalletBalanceDto getBalance(@PathVariable("walletAlias") String walletAlias, Principal principal) {
		QueryWalletBalanceCommand command = new QueryWalletBalanceCommand(new WalletId(walletAlias, principal.getName()));
		return QueryInfoMapper.INSTANCE.toOutput(queryBus.handle(command));
	}


	@GetMapping("/{walletAlias}/history")
	public List<WalletOperationDto> getHistory(@PathVariable("walletAlias") String walletAlias, Principal principal) {
		QueryWalletHistoryCommand command = new QueryWalletHistoryCommand(new WalletId(walletAlias, principal.getName()));
		return QueryInfoMapper.INSTANCE.toOutput(queryBus.handle(command));
	}

}
