package projectAl.use_cases.payment.application;

import projectAl.kernel.QueryHandler;
import projectAl.use_cases.payment.domain.Payment;
import projectAl.use_cases.payment.domain.PaymentRepository;

import java.util.List;

public class RetrievePaymentsByMemberHandler implements QueryHandler<RetrievePaymentsByMember, List<Payment>> {

    private final PaymentRepository paymentRepository;

    public RetrievePaymentsByMemberHandler(final PaymentRepository paymentRepository) {
        this.paymentRepository = paymentRepository;
    }

    @Override
    public List<Payment> handle(final RetrievePaymentsByMember query) {
        return this.paymentRepository.findByMemberId(query.id);
    }
}
