package com.aminabla.wallet.domain;

import java.math.BigDecimal;

import lombok.NonNull;
import lombok.Value;

@Value
public class Money {

    public static final Money ZERO = Money.of(0L);

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

    public boolean isGreaterThanOrEqualTo(Money money) {
        return this.amount.compareTo(money.amount) >= 0;
    }

    public boolean isGreaterThan(Money money) {
        return this.amount.compareTo(money.amount) >= 1;
    }

    public boolean isLessThan(Money money) {
        return this.amount.compareTo(money.amount) < 1;
    }

    public static Money of(double value) {
        return new Money(BigDecimal.valueOf(value));
    }

    public Money minus(Money money) {
        return new Money(this.amount.subtract(money.amount));
    }

    public Money plus(Money money) {
        return new Money(this.amount.add(money.amount));
    }

    public Money negate() {
        return new Money(this.amount.negate());
    }

}
