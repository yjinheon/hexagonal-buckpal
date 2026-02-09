package io.reflectoring.buckpal.account.domain;

import java.math.BigDecimal;
import java.math.BigInteger;

public record Money(BigInteger amount) {

    public static Money ZERO = Money.of(0L);

    public static Money of(long value) {
        return new Money(BigInteger.valueOf(value));
    }

    public static Money of(BigInteger value) {
        return new Money(value);
    }

    public static Money add(Money a, Money b) {
        return new Money(a.amount.add(b.amount));
    }

    public static Money subtract(Money a, Money b) {
        return new Money(a.amount.subtract(b.amount));
    }

    public boolean isPositiveOrZero() {
        return this.amount.compareTo(BigInteger.ZERO) >= 0;
    }

    public boolean isNegative() {
        return this.amount.compareTo(BigInteger.ZERO) < 0;
    }

    public boolean isPositive() {
        return this.amount.compareTo(BigInteger.ZERO) > 0;
    }

    public Money negate() {
        return new Money(this.amount.negate());
    }


}
