package use_cases.member.exposition;

public class SubscriptionRequest {

    public String type;
    public double price;

    @Override
    public String toString() {
        return "SubscriptionDTO{" +
                "type='" + type + '\'' +
                ", pricePerMonth='" + price + '\'' +
                '}';
    }
}
