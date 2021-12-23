package com.aminabla.wallet.infra.event.listener;

import com.aminabla.wallet.application.event.TransferEvent;

public interface WalletEventListener {
    void handleTransferEvent(TransferEvent event);
}
