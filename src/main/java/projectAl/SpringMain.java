package projectAl;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import projectAl.use_cases.member.application.*;
import projectAl.use_cases.member.domain.EmailAddress;
import projectAl.use_cases.member.domain.Member;
import projectAl.use_cases.member.domain.MemberId;
import projectAl.use_cases.member.domain.Subscription;
import projectAl.use_cases.member.exposition.SubscriptionResponse;
import projectAl.use_cases.member.infrastructure.BusinessSubscription;
import projectAl.use_cases.payment.application.*;
import projectAl.use_cases.payment.domain.CreditCard;
import projectAl.use_cases.payment.domain.CreditCardId;
import projectAl.use_cases.payment.domain.Payment;
import projectAl.use_cases.payment.domain.PaymentId;

import java.util.List;

//@SuppressWarnings("ALL")
@SpringBootApplication
public class SpringMain {
    public static void main(String[] args) {
        final ConfigurableApplicationContext applicationContext = SpringApplication.run(SpringMain.class, args);

        //--1. Create Member
        CreateMemberCommandHandler memberCommandHandler = applicationContext.getBean(CreateMemberCommandHandler.class);
        CreateMember createMember = new CreateMember("LYS", "Jean-Luc", new EmailAddress("jeanluc.lys@gmail.com"), "azerty", "PREMIUM");
        final MemberId memberId = memberCommandHandler.handle(createMember);

        //--2. Modify Member Subscription
        ModifyMemberSubscriptionCommandHandler modifyMemberSubscriptionCommandHandler = applicationContext.getBean(ModifyMemberSubscriptionCommandHandler.class);
        Subscription newSubscription = new BusinessSubscription();
        modifyMemberSubscriptionCommandHandler.handle(new ModifyMemberSubscription(memberId.getValue(), new SubscriptionResponse(newSubscription.subscriptionType(), newSubscription.pricePerMonth())));

        //--3. Retrieve all members
        RetrieveMembers retrieveMembers = new RetrieveMembers();
        RetrieveMembersHandler retrieveMembersHandler = applicationContext.getBean(RetrieveMembersHandler.class);
        final List<Member> members = retrieveMembersHandler.handle(retrieveMembers);
        members.forEach(System.out::println);

        //--4. Retrieve members with BUSINESS subscription
        RetrieveMembersBySubscription retrieveMembersBySubscription = new RetrieveMembersBySubscription("BUSINESS");
        RetrieveMembersBySubscriptionHandler retrieveMembersBySubscriptionHandler = applicationContext.getBean(RetrieveMembersBySubscriptionHandler.class);
        final List<Member> searchedMembers = retrieveMembersBySubscriptionHandler.handle(retrieveMembersBySubscription);
        searchedMembers.forEach(System.out::println);

        //--5. Create Payment// Member
        // Member
        RetrieveMemberByIdHandler retrieveMemberByIdHandler = applicationContext.getBean(RetrieveMemberByIdHandler.class);
        RetrieveMemberById retrieveMemberById = new RetrieveMemberById(memberId);
        Member createdMember = retrieveMemberByIdHandler.handle(retrieveMemberById).get(0);
        // Create Credit Card
        CreateCreditCardCommandHandler createCreditCardCommandHandler = applicationContext.getBean(CreateCreditCardCommandHandler.class);
        CreateCreditCard createCreditCard = new CreateCreditCard("1234133412357896", "22-04", "235");
        final CreditCardId creditCardId = createCreditCardCommandHandler.handle(createCreditCard);
        // Retrieve Credit Card created
        RetrieveCreditCardByIdHandler retrieveCreditCardByIdHandler = applicationContext.getBean(RetrieveCreditCardByIdHandler.class);
        RetrieveCreditCardById retrieveCreditCardById = new RetrieveCreditCardById(creditCardId);
        // Payment
        CreatePaymentCommandHandler paymentCommandHandler = applicationContext.getBean(CreatePaymentCommandHandler.class);
        CreditCard creditCard = retrieveCreditCardByIdHandler.handle(retrieveCreditCardById).get(0);
        CreatePayment createPayment = new CreatePayment(createdMember, creditCard);
        final PaymentId paymentId = paymentCommandHandler.handle(createPayment);

        //--6. Retrieve all payments for specific member
        RetrievePaymentsByMember retrievePaymentsByMember = new RetrievePaymentsByMember(memberId);
        RetrievePaymentsByMemberHandler retrievePaymentsByMemberHandler = applicationContext.getBean(RetrievePaymentsByMemberHandler.class);
        final List<Payment> payments = retrievePaymentsByMemberHandler.handle(retrievePaymentsByMember);
        payments.forEach(System.out::println);
    }
}
