package projectAl.use_cases.member.domain;

import projectAl.kernel.ValueObjectID;

public final class MemberId implements ValueObjectID {

    private final int value;

    private MemberId(int value) {
        this.value = value;
    }

    public static MemberId of(int value) {
        return new MemberId(value);
    }

    public int getValue() {
        return this.value;
    }

    public String getStringValue() {
        return String.valueOf(this.getValue());
    }

}
