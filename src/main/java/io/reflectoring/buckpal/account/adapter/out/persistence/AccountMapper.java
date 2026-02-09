package io.reflectoring.buckpal.account.adapter.out.persistence;


import io.reflectoring.buckpal.account.domain.Account;
import io.reflectoring.buckpal.account.domain.Account.AccountId;
import io.reflectoring.buckpal.account.domain.Activity;
import io.reflectoring.buckpal.account.domain.ActivityWindow;
import io.reflectoring.buckpal.account.domain.Money;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class AccountMapper {

    Account mapToDomain
            (
                    AccountEntity account
                    , List<ActivityEntity> activities
                    , Long withdrawalBalanec,
                    Long depositBalance ) {

        Money baselineBalance = Money.subtract(
                Money.of(depositBalance),
                Money.of(withdrawalBalance)
        );

        return Account.withId(
                new Account.AccountId(account.getId()),
                baselineBalance,
                mapToActivityWindow(activities)
        );

    }

    ActivityWindow mapToActivityWindow(List<ActivityEntity> activities) {
        List<Activity> mappedActivities = new ArrayList<>();
        for (ActivityEntity activity : activities) {
            mappedActivities.add(new Activity(
                    new AccountId(activity.getOwnerAccountId()),
                    new AccountId(activity.getSourceAccountId()),
                    new AccountId(activity.getTargetAccountId()),
                    activity.getTimestamp(),
                    Money.of(activity.getAmount())));
        }
        return new ActivityWindow(mappedActivities);
    }

    ActivityEntity mapToJpaEntity(Activity activity) {
        return new ActivityEntity(
                null,
                activity.timestamp(),
                activity.ownerAccountId().value(),
                activity.sourceAccountId().value(),
                activity.targetAccountId().value(),
                activity.money().amount().longValue());
    }


}
