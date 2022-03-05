package projectAl.use_cases.member.application;

import projectAl.kernel.Query;

public class RetrieveMembersBySubscription implements Query {

    public final String subscriptionType;

    public RetrieveMembersBySubscription(String subscriptionType) {
        this.subscriptionType = subscriptionType;
    }
}
