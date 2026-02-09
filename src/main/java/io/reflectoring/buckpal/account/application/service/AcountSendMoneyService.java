package io.reflectoring.buckpal.account.application.service;


import io.reflectoring.buckpal.account.application.port.in.SendMoneyCommand;
import io.reflectoring.buckpal.account.application.port.in.SendMoneyService;
import io.reflectoring.buckpal.account.application.port.out.LoadAccountPort;
import io.reflectoring.buckpal.account.application.port.out.UpdateAccountStatePort;
import io.reflectoring.buckpal.account.domain.Account;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
@Transactional
public class AccountSendMoneyService implements SendMoneyService {

    // Out Port(인터페이스)에 의존. 구현체(DB 어댑터)는 모름.
    private final LoadAccountPort loadAccountPort;
    private final UpdateAccountStatePort updateAccountStatePort;

    @Override
    public boolean sendMoney(SendMoneyCommand command) {
        // 1. 상태 로드
        Account sourceAccount = loadAccountPort.loadAccount(
                command.sourceAccountId(),
                java.time.LocalDateTime.now().minusDays(10));
        Account targetAccount = loadAccountPort.loadAccount(
                command.targetAccountId(),
                java.time.LocalDateTime.now().minusDays(10));

        // 2. 도메인 로직 실행 (상태 변경)
        if (!sourceAccount.withdraw(command.money(), targetAccount.getId().orElseThrow())) {
            return false;
        }

        // (입금 로직도 호출해야 하지만 생략)

        // 3. 상태 저장
        updateAccountStatePort.updateActivities(sourceAccount);
        updateAccountStatePort.updateActivities(targetAccount);

        return true;
    }
}
