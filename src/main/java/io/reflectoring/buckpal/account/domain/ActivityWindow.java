package io.reflectoring.buckpal.account.domain;

import io.reflectoring.buckpal.account.domain.Account.AccountId;
import lombok.NonNull;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/*
- 최근 입출금 내역의 목록 관리
- 이 목록 내에서 잔액 변동 계산
 */

public class ActivityWindow {

    private final List<Activity> activities;

    public ActivityWindow(@NonNull List<Activity> activities) {
        this.activities = activities;
    }

    public ActivityWindow(@NonNull Activity... activities) {
        this.activities = new ArrayList<>(Arrays.asList(activities));
    }

    public List<Activity> getActivities() {
        return Collections.unmodifiableList(this.activities);
    }

    public void addActivity(Activity activity) {
        this.activities.add(activity);
    }

    public Money calculateBalance(AccountId accountId) {
        Money depositBalance = activities.stream()
                .filter(a -> a.targetAccountId().equals(accountId))
                .map(Activity::money)
                .reduce(Money.ZERO, Money::add);

        Money withdrawalBalance = activities.stream()
                .filter(a -> a.sourceAccountId().equals(accountId))
                .map(Activity::money)
                .reduce(Money.ZERO, Money::add);

        return Money.add(depositBalance, withdrawalBalance.negate());
    }
}