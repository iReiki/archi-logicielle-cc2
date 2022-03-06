package projectAl.use_cases.payment.domain;

import projectAl.kernel.ValueObjectID;

public class PaymentId implements ValueObjectID {

    private final int value;

    private PaymentId(int value) {
        this.value = value;
    }

    public static PaymentId of(int value) {
        return new PaymentId(value);
    }

    @Override
    public int getValue() {
        return this.value;
    }
}
