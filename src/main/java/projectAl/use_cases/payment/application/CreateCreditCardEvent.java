package projectAl.use_cases.payment.application;

import projectAl.kernel.ApplicationEvent;
import projectAl.use_cases.payment.domain.CreditCardId;

public class CreateCreditCardEvent implements ApplicationEvent {

    private final CreditCardId creditCardId;

    public CreateCreditCardEvent(final CreditCardId creditCardId) {
        this.creditCardId = creditCardId;
    }
}
