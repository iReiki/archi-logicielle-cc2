package projectAl.use_cases.payment.domain;

import projectAl.kernel.ValueObjectID;

public class CreditCardId implements ValueObjectID {

    private final int value;

    private CreditCardId(int value) {
        this.value = value;
    }

    public static CreditCardId of(int value) {
        return new CreditCardId(value);
    }

    @Override
    public int getValue() {
        return this.value;
    }
}
