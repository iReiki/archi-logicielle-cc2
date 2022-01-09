package use_cases.member.application;

import kernel.Query;

public class RetrieveMembersBySubscription implements Query {

    public final String subscriptionType;

    public RetrieveMembersBySubscription(String subscriptionType) {
        this.subscriptionType = subscriptionType;
    }
}
