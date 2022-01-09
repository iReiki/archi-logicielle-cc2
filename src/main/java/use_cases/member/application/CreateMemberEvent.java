package use_cases.member.application;

import kernel.ApplicationEvent;
import use_cases.member.domain.MemberId;

public class CreateMemberEvent implements ApplicationEvent {

    private final MemberId memberId;

    public CreateMemberEvent(MemberId memberId) {
        this.memberId = memberId;
    }
}
