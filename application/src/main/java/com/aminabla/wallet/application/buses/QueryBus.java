package com.aminabla.wallet.application.buses;

import com.aminabla.wallet.application.commands.Query;

public interface QueryBus {

    <T, Q extends Query<T>> T handle(Q query);
}
