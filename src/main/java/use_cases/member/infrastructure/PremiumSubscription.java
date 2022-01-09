package use_cases.member.infrastructure;

import use_cases.member.domain.Subscription;
import use_cases.member.domain.SubscriptionType;

public final class PremiumSubscription implements Subscription {
    @Override
    public String subscriptionType() {
        return SubscriptionType.PREMIUM.name();
    }

    @Override
    public double pricePerMonth() {
        return 49.99;
    }
}
