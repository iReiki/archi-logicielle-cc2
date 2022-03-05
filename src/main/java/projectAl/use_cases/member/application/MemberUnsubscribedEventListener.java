package projectAl.use_cases.member.application;

import projectAl.kernel.EventListener;

public class MemberUnsubscribedEventListener implements EventListener<MemberUnsubscribedEvent> {

    @Override
    public void listenTo(final MemberUnsubscribedEvent event) {
        System.out.println("Listening ModifyMemberSubscriptionEvent.");
    }
}
