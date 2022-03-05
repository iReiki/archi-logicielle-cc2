package projectAl.use_cases.member.application;

import projectAl.kernel.Command;
import projectAl.use_cases.member.exposition.SubscriptionResponse;

public class ModifyMemberSubscription implements Command {

    public final int memberId;
    public final SubscriptionResponse subscription;

    public ModifyMemberSubscription(int memberId, SubscriptionResponse subscription) {
        this.memberId = memberId;
        this.subscription = subscription;
    }
}
