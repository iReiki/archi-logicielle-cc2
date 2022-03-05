package projectAl.use_cases.member.infrastructure;

import projectAl.use_cases.member.domain.Subscription;
import projectAl.use_cases.member.domain.SubscriptionType;

public final class BusinessSubscription implements Subscription {
    @Override
    public String subscriptionType() {
        return SubscriptionType.BUSINESS.name();
    }

    @Override
    public double pricePerMonth() {
        return 29.99;
    }
}
