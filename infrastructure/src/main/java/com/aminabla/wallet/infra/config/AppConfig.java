package com.aminabla.wallet.infra.config;

import com.aminabla.wallet.application.ports.api.WalletInfo;
import com.aminabla.wallet.application.ports.api.WalletOperations;
import com.aminabla.wallet.application.ports.spi.WalletStatePort;
import com.aminabla.wallet.application.ports.spi.LoadWalletPort;
import com.aminabla.wallet.application.service.WalletInfoService;
import com.aminabla.wallet.application.service.WalletService;
import com.aminabla.wallet.infra.persistence.WalletMapper;
import com.aminabla.wallet.infra.persistence.AccountPersistenceAdapter;
import com.aminabla.wallet.infra.persistence.WalletRepository;
import com.aminabla.wallet.infra.persistence.WalletOperationsRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig {

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
