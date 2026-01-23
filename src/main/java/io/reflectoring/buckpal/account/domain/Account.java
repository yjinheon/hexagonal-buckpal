package io.reflectoring.buckpal.account.domain;

import java.time.LocalDateTime;

public class Account {

    private final AccountId id;
    private final Money baselineBalance;
    private final ActivityWindow activityWindow;

    public static Account withoutId(Money baselineBalance, ActivityWindow activityWindow) {
        return new Account(null, baselineBalance, activityWindow);
    }

    public static Account withId(AccountId accountId, Money baselineBalance, ActivityWindow activityWindow) {
        return new Account(accountId, baselineBalance, activityWindow);
    }

    // check if withdrawal is available
    public boolean withdraw(Money money, AccountId targetAccountId) {
        if (!mayWithdraw(money)) {
            return false;
        }

        Activity withdrawal = new Activity(
                this.id,
                this.id,
                targetAccountId,
                LocalDateTime.now(),
                money);
        this.activityWindow.addActivity(withdrawal);

        return true;
    }

    private boolean mayWithdraw (Money money) {
        return Money.add(
                this.calculateBalance(),
                money.negate()

        ).isPositiveorZero();

    }

    public Money calculateBalance() {
        return Money.add(this.baselineBalance,
                this.activityWindow.calculateBalance(this.id);
        )
    }

    public record AccountId(Long value) {}
}
