package use_cases.member.application;

import kernel.EventListener;

public class ModifyMemberSubscriptionEventListener implements EventListener<ModifyMemberSubscriptionEvent> {

    @Override
    public void listenTo(final ModifyMemberSubscriptionEvent event) {
        System.out.println("Listening ModifyMemberSubscriptionEvent.");
    }
}
