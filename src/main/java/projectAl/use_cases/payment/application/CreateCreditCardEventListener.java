package projectAl.use_cases.payment.application;

import projectAl.kernel.EventListener;

public class CreateCreditCardEventListener implements EventListener<CreateCreditCardEvent> {
    @Override
    public void listenTo(final CreateCreditCardEvent event) {
        System.out.println("Listening CreateCreditCardEvent.");
    }
}
