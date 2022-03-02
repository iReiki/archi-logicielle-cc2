package use_cases.member.application;

import kernel.ApplicationEvent;
import use_cases.member.domain.MemberId;

public class MemberUnsubscribedEvent implements ApplicationEvent {

    private final MemberId memberId;

    public MemberUnsubscribedEvent(MemberId memberId) {
        this.memberId = memberId;
    }
}
