package projectAl.use_cases.member.exposition;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class EmailAddressRequest {

    @NotNull
    @NotBlank
    public String email;

    @Override
    public String toString() {
        return "EmailAddressDTO{" +
                "email='" + email + '\'' +
                '}';
    }
}
