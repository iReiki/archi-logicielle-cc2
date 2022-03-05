package projectAl.use_cases.member.domain;

import projectAl.kernel.Entity;

import java.util.Objects;

public final class Member implements Entity<MemberId> {

    private final MemberId memberId;
    private final String lastname;
    private final String firstname;
    private final EmailAddress email;
    private String password;
    private Subscription subscription;


    private Member(final MemberId memberId, final String lastname, final String firstname, final EmailAddress email, final String password,
                   final Subscription subscription) {
        this.memberId = memberId;
        this.lastname = lastname;
        this.firstname = firstname;
        this.email = email;
        this.password = password;
        this.subscription = subscription;
    }

    public static Member of(final MemberId memberId, final String lastname, final String firstname, final EmailAddress email, final String password,
                            final Subscription subscription) {
        return new Member(memberId, lastname, firstname, email, password, subscription);
    }

    public MemberId getMemberId() {
        return this.memberId;
    }

    public String getLastname() { return this.lastname; }

    public String getFirstname() { return this.firstname; }

    public String getPassword() { return this.password; }

    public EmailAddress getEmail() { return this.email; }

    public Subscription getSubscription() { return this.subscription; }

    public void changeSubscription(Subscription subscription) { this.subscription = subscription; }

    public void changePassword(String newPassword) {
        this.password = Objects.requireNonNull(newPassword);
    }

    @Override
    public String toString() {
        String hiddenPassword = "*".repeat(this.password.length());
        //String hiddenPassword = this.password;
        return "Member {" +
                "memberId=" + this.memberId.getValue() +
                ", lastname='" + this.lastname + '\'' +
                ", firstname='" + this.firstname + '\'' +
                ", email='" + this.email.getValue() + '\'' +
                ", password='" + hiddenPassword + '\'' +
                ", subscription=" + this.subscription.subscriptionType() +
                '}';
    }

    @Override
    public MemberId id() {
        return this.memberId;
    }
}
