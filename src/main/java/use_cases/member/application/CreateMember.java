package use_cases.member.application;

import kernel.Command;
import use_cases.member.domain.EmailAddress;
import use_cases.member.domain.Subscription;
import use_cases.member.domain.SubscriptionType;
import use_cases.member.infrastructure.BusinessSubscription;
import use_cases.member.infrastructure.DefaultSubscription;
import use_cases.member.infrastructure.PremiumSubscription;

public final class CreateMember implements Command {

    public final String lastname;
    public final String firstname;
    public final EmailAddress emailAddress;
    public final String password;
    public final Subscription subscription;

    public CreateMember(final String lastname, final String firstname, final EmailAddress emailAddress, final String password, final String subscriptionType) {
        this.lastname = lastname;
        this.firstname = firstname;
        this.emailAddress = emailAddress;
        this.password = password;
        this.subscription = this.setSubscription(subscriptionType);
    }

    private Subscription setSubscription(String subscriptionType) {
        Subscription s;
        if (subscriptionType.equals(SubscriptionType.BUSINESS.name())) {
            s = new BusinessSubscription();
        } else if (subscriptionType.equals(SubscriptionType.PREMIUM.name())) {
            s = new PremiumSubscription();
        } else {
            s = new DefaultSubscription();
        }
        return s;
    }

}
