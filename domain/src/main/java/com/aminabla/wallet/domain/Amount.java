package com.aminabla.wallet.domain;

import java.math.BigDecimal;

import lombok.NonNull;
import lombok.Value;

@Value
public class Amount {

    public static Amount ZERO = Amount.of(0L);

    @NonNull
    private final BigDecimal amount;

    public boolean isPositiveOrZero() {
        return this.isGreaterThanOrEqualTo(ZERO);
    }

    public boolean isNegative() {
        return isLessThan(ZERO);
    }

    public boolean isPositive() {
        return isGreaterThan(ZERO);
    }

    public boolean isGreaterThanOrEqualTo(Amount money) {
        return this.amount.compareTo(money.amount) >= 0;
    }

    public boolean isGreaterThan(Amount money) {
        return this.amount.compareTo(money.amount) >= 1;
    }

    public boolean isLessThan(Amount money) {
        return this.amount.compareTo(money.amount) < 1;
    }

    public static Amount of(double value) {
        return new Amount(BigDecimal.valueOf(value));
    }

    public Amount minus(Amount money) {
        return new Amount(this.amount.subtract(money.amount));
    }

    public Amount plus(Amount money) {
        return new Amount(this.amount.add(money.amount));
    }

    public Amount negate() {
        return new Amount(this.amount.negate());
    }

}
