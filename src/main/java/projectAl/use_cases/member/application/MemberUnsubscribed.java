package projectAl.use_cases.member.application;

import projectAl.kernel.Command;
import projectAl.use_cases.member.exposition.SubscriptionResponse;

public class MemberUnsubscribed implements Command {

    public final int memberId;
    public final SubscriptionResponse subscription;

    public MemberUnsubscribed(int memberId, SubscriptionResponse subscription) {
        this.memberId = memberId;
        this.subscription = subscription;
    }
}
