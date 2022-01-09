package use_cases.member.exposition;

public class MemberResponse {

    public String id;
    public String lastname;
    public String firstname;
    public EmailAddressResponse emailAddress;
    public String password;
    public SubscriptionResponse subscription;

    public MemberResponse(String id, String lastname, String firstname, EmailAddressResponse emailAddress, String password, SubscriptionResponse subscription) {
        this.id = id;
        this.lastname = lastname;
        this.firstname = firstname;
        this.emailAddress = emailAddress;
        this.password = password;
        this.subscription = subscription;
    }

    @Override
    public String toString() {
        return "MemberDTO{" +
                "id=" + this.id +
                ", lastname='" + this.lastname + '\'' +
                ", firstname='" + this.firstname + '\'' +
                ", emailAddress=" + this.emailAddress +
                ", password='" + this.password + '\'' +
                ", subscription=" + this.subscription +
                '}';
    }
}
