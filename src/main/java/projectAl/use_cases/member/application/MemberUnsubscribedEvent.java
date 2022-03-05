package projectAl.use_cases.member.application;

import projectAl.kernel.ApplicationEvent;
import projectAl.use_cases.member.domain.MemberId;

public class MemberUnsubscribedEvent implements ApplicationEvent {

    private final MemberId memberId;

    public MemberUnsubscribedEvent(MemberId memberId) {
        this.memberId = memberId;
    }
}
