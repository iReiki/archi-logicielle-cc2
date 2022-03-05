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
    }
}
