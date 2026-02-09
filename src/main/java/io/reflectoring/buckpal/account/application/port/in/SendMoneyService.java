package io.reflectoring.buckpal.account.application.port.in;

import io.reflectoring.buckpal.account.domain.Account;
import java.math.BigDecimal;

public interface SendMoneyService {

    boolean sendMoney(SendMoneyCommand command);
}
