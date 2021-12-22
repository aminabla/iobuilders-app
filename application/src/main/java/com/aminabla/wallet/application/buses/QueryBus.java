package com.aminabla.wallet.application.buses;

import com.aminabla.wallet.application.handlers.Query;

public interface QueryBus {

    <T> T handle(Query<T> query);
}
