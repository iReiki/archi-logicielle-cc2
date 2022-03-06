package projectAl.use_cases.payment.application;

import projectAl.kernel.ApplicationEvent;
import projectAl.use_cases.payment.domain.PaymentId;

public class CreatePaymentEvent implements ApplicationEvent {

    private final PaymentId paymentId;

    public CreatePaymentEvent(final PaymentId paymentId) {
        this.paymentId = paymentId;
    }
}
