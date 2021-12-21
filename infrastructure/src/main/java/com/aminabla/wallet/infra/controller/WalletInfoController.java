package com.aminabla.wallet.infra.controller;


import com.aminabla.wallet.application.ports.api.WalletInfo;
import com.aminabla.wallet.application.ports.api.commands.QueryWalletBalanceCommand;
import com.aminabla.wallet.application.ports.api.commands.QueryWalletHistoryCommand;
import com.aminabla.wallet.domain.Wallet.WalletId;
import com.aminabla.wallet.infra.controller.dto.output.WalletBalanceDto;
import com.aminabla.wallet.infra.controller.dto.output.WalletOperationDto;
import com.aminabla.wallet.infra.controller.mapper.QueryInfoMapper;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/wallets")
class WalletInfoController {

	private final WalletInfo walletBalanceInfo;

	public WalletInfoController(WalletInfo walletBalanceInfo) {
		this.walletBalanceInfo = walletBalanceInfo;
	}


	@GetMapping("/{walletId}")
	WalletBalanceDto getBalance(@PathVariable("walletId") Long walletId) {
		QueryWalletBalanceCommand command = new QueryWalletBalanceCommand(new WalletId(walletId));
		return QueryInfoMapper.INSTANCE.toOutput(walletBalanceInfo.getBalance(command));
	}


	@GetMapping("/{walletId}/history")
	List<WalletOperationDto> getHistory(@PathVariable("walletId") Long walletId) {
		QueryWalletHistoryCommand command = new QueryWalletHistoryCommand(new WalletId(walletId));
		return QueryInfoMapper.INSTANCE.toOutput(walletBalanceInfo.getOperationsHistory(command));
	}

}
