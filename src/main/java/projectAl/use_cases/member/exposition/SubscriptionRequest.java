package projectAl.use_cases.member.exposition;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class SubscriptionRequest {

    @NotNull
    @NotBlank
    public String type;

    @NotNull
    @NotBlank
    public double price;

    @Override
    public String toString() {
        return "SubscriptionDTO{" +
                "type='" + type + '\'' +
                ", pricePerMonth='" + price + '\'' +
                '}';
    }
}
