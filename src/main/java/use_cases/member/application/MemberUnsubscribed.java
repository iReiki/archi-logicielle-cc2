package use_cases.member.application;

import kernel.Command;
import use_cases.member.exposition.SubscriptionResponse;

public class MemberUnsubscribed implements Command {

    public final int memberId;
    public final SubscriptionResponse subscription;

    public MemberUnsubscribed(int memberId, SubscriptionResponse subscription) {
        this.memberId = memberId;
        this.subscription = subscription;
    }
}
