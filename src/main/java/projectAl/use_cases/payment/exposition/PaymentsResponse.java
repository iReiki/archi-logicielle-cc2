package projectAl.use_cases.payment.exposition;

import java.util.List;

public class PaymentsResponse {

    private final List<PaymentResponse> payments;

    public PaymentsResponse(final List<PaymentResponse> payments) {
        this.payments = payments;
    }

    public List<PaymentResponse> getPayments() { return this.payments; }

}
