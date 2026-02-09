package io.reflectoring.buckpal.account.domain;

import io.reflectoring.buckpal.account.domain.Account.AccountId;
import java.time.LocalDateTime;

public record Activity(
        AccountId id,
        AccountId ownerAccountId,
        AccountId sourceAccountId,
        AccountId targetAccountId,
        LocalDateTime timestamp,
        Money money
) {
    // ID가 없는 새로운 활동을 생성할 때 사용하는 생성자
    public Activity(AccountId ownerAccountId,
                    AccountId sourceAccountId,
                    AccountId targetAccountId,
                    LocalDateTime timestamp,
                    Money money) {
        this(null, ownerAccountId, sourceAccountId, targetAccountId, timestamp, money);
    }
}