package use_cases.member.domain;

import kernel.exception.InvalidEmailException;

import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public final class EmailAddress {
    private final String value;

    public EmailAddress(String value) {
        if (!this.checkEmail(value)) {
            throw new InvalidEmailException(value);
        }
        this.value = value;
    }

    public EmailAddress change(String value) {
        return new EmailAddress(value);
    }

    public String getValue() { return this.value; }

    @Override
    public String toString() {
        return this.value;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        EmailAddress that = (EmailAddress) o;
        return Objects.equals(this.value, that.value);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.value);
    }

    private boolean checkEmail(String email) {
        Pattern p = Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$", Pattern.CASE_INSENSITIVE);
        Matcher matcher = p.matcher(email);
        return matcher.find();
    }
}
