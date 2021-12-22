package com.aminabla.wallet.infra.config;

import com.aminabla.wallet.application.buses.CommandBus;
import com.aminabla.wallet.application.buses.QueryBus;
import com.aminabla.wallet.application.buses.impl.CommandBusImpl;
import com.aminabla.wallet.application.buses.impl.QueryBusImpl;
import com.aminabla.wallet.application.handlers.Command;
import com.aminabla.wallet.application.handlers.CommandHandler;
import com.aminabla.wallet.application.handlers.Query;
import com.aminabla.wallet.application.handlers.QueryHandler;
import com.aminabla.wallet.application.handlers.impl.*;
import com.aminabla.wallet.application.ports.api.WalletInfo;
import com.aminabla.wallet.application.ports.api.WalletOperations;
import com.aminabla.wallet.application.ports.spi.LoadWalletPort;
import com.aminabla.wallet.application.ports.spi.WalletStatePort;
import com.aminabla.wallet.application.service.WalletInfoService;
import com.aminabla.wallet.application.service.WalletService;
import com.aminabla.wallet.infra.persistence.AccountPersistenceAdapter;
import com.aminabla.wallet.infra.persistence.WalletMapper;
import com.aminabla.wallet.infra.persistence.WalletOperationsRepository;
import com.aminabla.wallet.infra.persistence.WalletRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class AppConfig {


    @Bean
    public QueryHandler<?, ? extends Query<?>> queryWalletBalanceCommandQueryHandler(WalletInfo walletInfoService){
        return new QueryWalletBalanceCommandHandler(walletInfoService);
    }

    @Bean
    public QueryHandler<?, ? extends Query<?>> queryWalletHistoryCommandQueryHandler(WalletInfo walletInfoService){
        return new QueryWalletHistoryCommandHandler(walletInfoService);
    }

    @Bean
    public CommandHandler<?> createWalletCommandCommandHandler(WalletOperations walletService){
        return new CreateWalletCommandHandler(walletService);
    }

    @Bean
    public CommandHandler<?> depositMoneyCommandCommandHandler(WalletOperations walletService){
        return new DepositMoneyCommandHandler(walletService);
    }

    @Bean
    public CommandHandler<?> withdrawMoneyCommandCommandHandler(WalletOperations walletService){
        return new WithdrawMoneyCommandHandler(walletService);
    }

    @Bean
    public QueryBus queryBus(List<QueryHandler<?, ? super Query<?>>> handlers){
        return new QueryBusImpl(handlers);
    }

    @Bean
    public CommandBus commandBus(List<CommandHandler<? super Command>> handlers){
        return new CommandBusImpl(handlers);
    }

    @Bean
    public WalletOperations walletOperations(LoadWalletPort loadWalletPort, WalletStatePort walletStatePort){
        return new WalletService(loadWalletPort, walletStatePort);
    }

    @Bean
    public WalletInfo walletInfo(LoadWalletPort loadWalletPort, WalletStatePort walletStatePort){
        return new WalletInfoService(loadWalletPort);
    }

    @Bean
    public LoadWalletPort loadWalletPort(WalletRepository walletRepository, WalletOperationsRepository walletOperationsRepository){
        return new AccountPersistenceAdapter(walletRepository, walletOperationsRepository, new WalletMapper());
    }

    @Bean
    public WalletStatePort walletStatePort(WalletRepository walletRepository, WalletOperationsRepository walletOperationsRepository){
        return new AccountPersistenceAdapter(walletRepository, walletOperationsRepository, new WalletMapper());
    }

}
