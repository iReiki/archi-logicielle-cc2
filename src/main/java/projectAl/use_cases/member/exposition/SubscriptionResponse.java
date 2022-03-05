package projectAl.use_cases.member.exposition;

public class SubscriptionResponse {

    public String type;
    public double price;

    public SubscriptionResponse(String type, double price) {
        this.type = type;
        this.price = price;
    }

    @Override
    public String toString() {
        return "SubscriptionDTO{" +
                "type='" + type + '\'' +
                ", pricePerMonth='" + price + '\'' +
                '}';
    }
}
