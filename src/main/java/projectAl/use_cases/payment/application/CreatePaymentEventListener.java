package projectAl.use_cases.payment.application;

import projectAl.kernel.EventListener;

public class CreatePaymentEventListener implements EventListener<CreatePaymentEvent> {

    @Override
    public void listenTo(final CreatePaymentEvent event) {
        System.out.println("Listening CreatePaymentEvent.");
    }
}
