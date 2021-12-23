package com.aminabla.wallet.infra.event.publisher.impl;

import com.aminabla.wallet.application.event.WalletEvent;
import com.aminabla.wallet.application.event.publisher.EventPublisher;
import org.springframework.context.ApplicationEventPublisher;

public class EventPublisherImpl implements EventPublisher {

    private final ApplicationEventPublisher applicationEventPublisher;

    public EventPublisherImpl(ApplicationEventPublisher applicationEventPublisher) {
        this.applicationEventPublisher = applicationEventPublisher;
    }

    @Override
    public void publish(WalletEvent event) {
        applicationEventPublisher.publishEvent(event);
    }
}
