package projectAl.use_cases.member.application;

import projectAl.kernel.ApplicationEvent;
import projectAl.use_cases.member.domain.MemberId;

public class ModifyMemberSubscriptionEvent implements ApplicationEvent {

    private final MemberId memberId;

    public ModifyMemberSubscriptionEvent(MemberId memberId) {
        this.memberId = memberId;
    }
}
