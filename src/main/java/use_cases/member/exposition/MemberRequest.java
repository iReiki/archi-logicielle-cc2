package use_cases.member.exposition;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class MemberRequest {

    @NotNull
    @NotBlank
    public String lastname;

    @NotNull
    @NotBlank
    public String firstname;

    @NotNull
    public EmailAddressRequest emailAddress;

    @NotNull
    @NotBlank
    public String password;

    @NotNull
    public SubscriptionRequest subscription;
}
