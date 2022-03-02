package use_cases.member.infrastructure;

import use_cases.member.domain.Subscription;
import use_cases.member.domain.SubscriptionType;

public final class DefaultSubscription implements Subscription {

    @Override
    public String subscriptionType() {
        return SubscriptionType.FREE.name();
    }

    @Override
    public double pricePerMonth() {
        return 0.0;
    }
}
