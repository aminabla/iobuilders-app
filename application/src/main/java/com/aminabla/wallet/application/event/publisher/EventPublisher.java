package com.aminabla.wallet.application.event.publisher;

import com.aminabla.wallet.application.event.WalletEvent;

public interface EventPublisher {
    void publish (WalletEvent event);
}
