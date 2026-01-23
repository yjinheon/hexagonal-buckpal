package io.reflectoring.buckpal.account.application.port.in;

import io.reflectoring.buckpal.account.domain.Account.AccountId;
import io.reflectoring.buckpal.account.domain.Money;
import jakarta.validation.constraints.NotNull;
import io.reflectoring.buckpal.common.SelfValidating;

// Java 21 Record 활용
public record SendMoneyCommand(
        @NotNull AccountId sourceAccountId,
        @NotNull AccountId targetAccountId,
        @NotNull Money money
) {
    public SendMoneyCommand {
        // Compact Constructor: 생성자 내부에서 검증 로직 수행
        if (sourceAccountId == null || targetAccountId == null || money == null) {
            throw new IllegalArgumentException("All fields must not be null");
        }
        if (!money.isPositive()) {
            throw new IllegalArgumentException("Money must be positive");
        }
        // SelfValidating 등을 상속받아 Bean Validation을 활용 가능
    }
}
