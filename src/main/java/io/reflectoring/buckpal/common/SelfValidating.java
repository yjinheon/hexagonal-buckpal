package io.reflectoring.buckpal.common;

import io.reflectoring.buckpal.account.domain.Account;

public abstract class SelfValidating<T> {
    // bean validation 수동호출
    protected void validate(Account account) {
        //validation logic

    }
}
