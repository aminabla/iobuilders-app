package com.aminabla.wallet.application.event;

import com.aminabla.wallet.domain.Identifier;
import lombok.Value;

import java.util.UUID;

@Value
public class EventId implements Identifier<UUID> {
    UUID identifier;
}
