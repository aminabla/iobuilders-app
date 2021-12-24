package com.aminabla.wallet.infra.config;

import com.aminabla.wallet.application.buses.CommandBus;
import com.aminabla.wallet.application.buses.CommandValidator;
import com.aminabla.wallet.application.buses.QueryBus;
import com.aminabla.wallet.application.buses.impl.CommandBusImpl;
import com.aminabla.wallet.application.buses.impl.QueryBusImpl;
import com.aminabla.wallet.application.buses.impl.ValidatorImpl;
import com.aminabla.wallet.application.commands.impl.*;
import com.aminabla.wallet.application.commands.Command;
import com.aminabla.wallet.application.event.publisher.EventPublisher;
import com.aminabla.wallet.application.handlers.CommandHandler;
import com.aminabla.wallet.application.commands.Query;
import com.aminabla.wallet.application.handlers.QueryHandler;
import com.aminabla.wallet.application.handlers.impl.*;
import com.aminabla.wallet.domain.Balance;
import com.aminabla.wallet.domain.WalletOperation;
import com.aminabla.wallet.domain.ports.api.WalletInfo;
import com.aminabla.wallet.domain.ports.api.WalletOperations;
import com.aminabla.wallet.domain.ports.spi.LoadWalletPort;
import com.aminabla.wallet.domain.ports.spi.WalletStatePort;
import com.aminabla.wallet.domain.service.WalletInfoService;
import com.aminabla.wallet.domain.service.WalletService;
import com.aminabla.wallet.infra.controller.auth.AuthenticationAccessHelper;
import com.aminabla.wallet.infra.controller.auth.impl.AuthenticationAccessHelperImpl;
import com.aminabla.wallet.infra.event.listener.WalletEventListener;
import com.aminabla.wallet.infra.event.publisher.impl.EventPublisherImpl;
import com.aminabla.wallet.infra.event.listener.impl.WalletEventListenerImpl;
import com.aminabla.wallet.infra.mapper.WalletMapper;
import com.aminabla.wallet.infra.persistence.AccountPersistenceAdapter;
import com.aminabla.wallet.infra.mapper.WalletMapperImpl;
import com.aminabla.wallet.infra.persistence.repository.WalletOperationsRepository;
import com.aminabla.wallet.infra.persistence.repository.WalletRepository;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class AppConfig {


    @Bean
    public QueryHandler<Balance, QueryWalletBalanceCommand> queryWalletBalanceCommandQueryHandler(WalletInfo walletInfoService) {
        return new QueryWalletBalanceCommandHandler(walletInfoService);
    }

    @Bean
    public QueryHandler<List<WalletOperation>, QueryWalletHistoryCommand> queryWalletHistoryCommandQueryHandler(WalletInfo walletInfoService) {
        return new QueryWalletHistoryCommandHandler(walletInfoService);
    }

    @Bean
    public CommandHandler<CreateWalletCommand> createWalletCommandCommandHandler(WalletOperations walletService) {
        return new CreateWalletCommandHandler(walletService);
    }

    @Bean
    public CommandHandler<DepositMoneyCommand> depositMoneyCommandCommandHandler(WalletOperations walletService) {
        return new DepositMoneyCommandHandler(walletService);
    }

    @Bean
    public CommandHandler<WithdrawMoneyCommand> withdrawMoneyCommandCommandHandler(WalletOperations walletService) {
        return new WithdrawMoneyCommandHandler(walletService);
    }

    @Bean
    public CommandHandler<TransferMoneyCommand> transferMoneyCommandCommandHandler(WalletOperations walletService, EventPublisher eventPublisher) {
        return new TransferMoneyCommandHandler(walletService, eventPublisher);
    }

    @Bean
    public QueryBus queryBus(List<QueryHandler<?, ? extends Query<?>>> handlers, CommandValidator<Query<?>> validator) {
        return new QueryBusImpl(handlers, validator);
    }

    @Bean
    public CommandBus commandBus(List<CommandHandler<? extends Command>> handlers, CommandValidator<Command> validator) {
        return new CommandBusImpl(handlers, validator);
    }

    @Bean
    public CommandValidator<?> commandValidator() {
        return new ValidatorImpl<>();
    }

    @Bean
    public WalletOperations walletOperations(LoadWalletPort loadWalletPort, WalletStatePort walletStatePort) {
        return new WalletService(loadWalletPort, walletStatePort);
    }

    @Bean
    public WalletInfo walletInfo(LoadWalletPort loadWalletPort) {
        return new WalletInfoService(loadWalletPort);
    }

    @Bean
    public LoadWalletPort loadWalletPort(WalletRepository walletRepository, WalletOperationsRepository walletOperationsRepository) {
        return new AccountPersistenceAdapter(walletRepository, walletOperationsRepository, walletMapper());
    }

    @Bean
    public WalletStatePort walletStatePort(WalletRepository walletRepository, WalletOperationsRepository walletOperationsRepository) {
        return new AccountPersistenceAdapter(walletRepository, walletOperationsRepository, walletMapper());
    }

    @Bean
    public WalletMapper walletMapper(){
        return new WalletMapperImpl();
    }

    @Bean
    public EventPublisher walletEventPublisher(ApplicationEventPublisher applicationEventPublisher){
        return new EventPublisherImpl(applicationEventPublisher);
    }

    @Bean
    public WalletEventListener walletEventListener(CommandBus commandBus){
        return new WalletEventListenerImpl(commandBus);
    }

    @Bean
    public AuthenticationAccessHelper authenticationHelper(){
        return new AuthenticationAccessHelperImpl();
    }

}
