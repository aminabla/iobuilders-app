package com.aminabla.wallet.application.handlers.impl;

import com.aminabla.wallet.application.commands.impl.CreateWalletCommand;
import com.aminabla.wallet.domain.Wallet.WalletId;
import com.aminabla.wallet.domain.ports.api.WalletOperations;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class CreateWalletCommandHandlerTest {

    @Mock
    WalletOperations walletOperations;

    @InjectMocks
    CreateWalletCommandHandler createWalletCommandHandler;

    @Test
    void handle() {
        //given
        WalletId walletId = new WalletId("alias", "user");
        CreateWalletCommand command = new CreateWalletCommand(walletId);

        //when
        createWalletCommandHandler.handle(command);

        //then
        verify(walletOperations, times(1)).create(walletId);
    }

    @Test
    void canHandle() {
        //given
        WalletId walletId = new WalletId("alias", "user");
        CreateWalletCommand command = new CreateWalletCommand(new WalletId("alias", "user"));

        //then
        assertThat(createWalletCommandHandler.canHandle(command)).isTrue();
    }
}