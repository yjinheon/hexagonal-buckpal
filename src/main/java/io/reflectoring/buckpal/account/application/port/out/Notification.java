package io.reflectoring.buckpal.account.application.port.out;

import io.reflectoring.buckpal.account.domain.Account.AccountId;
import io.reflectoring.buckpal.account.domain.Money;

public record Notification(
        AccountId targetAccountId,
        Money money,
        String message
) {
    public static Notification of(AccountId targetAccountId, Money money, String message) {
        return new Notification(targetAccountId, money, message);
    }
}
