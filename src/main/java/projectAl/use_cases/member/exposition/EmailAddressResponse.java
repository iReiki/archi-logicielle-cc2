package projectAl.use_cases.member.exposition;

public class EmailAddressResponse {

    public final String email;

    public EmailAddressResponse(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return "EmailAddressDTO{" +
                "email='" + this.email + '\'' +
                '}';
    }
}
