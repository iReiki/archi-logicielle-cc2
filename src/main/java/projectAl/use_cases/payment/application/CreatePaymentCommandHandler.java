package projectAl.use_cases.payment.application;

import projectAl.kernel.CommandHandler;
import projectAl.kernel.Event;
import projectAl.kernel.EventDispatcher;
import projectAl.use_cases.payment.domain.Payment;
import projectAl.use_cases.payment.domain.PaymentId;
import projectAl.use_cases.payment.domain.PaymentRepository;

public class CreatePaymentCommandHandler implements CommandHandler<CreatePayment, PaymentId> {

    private final PaymentRepository paymentRepository;
    private final EventDispatcher<Event> eventEventDispatcher;

    public CreatePaymentCommandHandler(final PaymentRepository paymentRepository, final EventDispatcher<Event> eventEventDispatcher) {
        this.paymentRepository = paymentRepository;
        this.eventEventDispatcher = eventEventDispatcher;
    }

    @Override
    public PaymentId handle(final CreatePayment createPayment) {
        final PaymentId paymentId = paymentRepository.nextIdentity();
        Payment payment = Payment.of(paymentId, createPayment.member, createPayment.creditCard);
        paymentRepository.add(payment);
        eventEventDispatcher.dispatch(new CreatePaymentEvent(paymentId));
        return paymentId;
    }
}
