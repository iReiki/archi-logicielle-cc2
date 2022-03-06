package projectAl.use_cases.payment.application;

import projectAl.kernel.QueryHandler;
import projectAl.use_cases.payment.domain.Payment;
import projectAl.use_cases.payment.domain.PaymentRepository;

import java.util.List;

public class RetrievePaymentsHandler implements QueryHandler<RetrievePayments, List<Payment>> {

    private final PaymentRepository paymentRepository;

    public RetrievePaymentsHandler(final PaymentRepository paymentRepository) {
        this.paymentRepository = paymentRepository;
    }

    @Override
    public List<Payment> handle(final RetrievePayments query) {
        return this.paymentRepository.findAll();
    }
}
