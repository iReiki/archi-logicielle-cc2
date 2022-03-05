package projectAl.use_cases.member.application;

import projectAl.kernel.EventListener;

public class CreateMemberEventListener implements EventListener<CreateMemberEvent> {

    @Override
    public void listenTo(final CreateMemberEvent event) {
        System.out.println("Listening CreateMemberEvent.");
    }
}
