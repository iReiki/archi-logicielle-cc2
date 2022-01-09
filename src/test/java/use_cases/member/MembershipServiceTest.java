package use_cases.member;

import org.junit.jupiter.api.Test;
import use_cases.member.application.CreateMember;
import use_cases.member.domain.*;
import use_cases.member.infrastructure.InMemoryMemberRepository;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class MembershipServiceTest {

    @Test
    void createUser() {
        MemberRepository memberRepository = new InMemoryMemberRepository();
        MembershipService membershipService = new MembershipService(memberRepository);

        CreateMember createdMember = new CreateMember("LYS", "JL", new EmailAddress("jeanluc.lys@gmail.com"), "PASSWORD", "BUSINESS");
        final MemberId memberId = membershipService.createMember(createdMember);

        final Member storedMember = memberRepository.findById(memberId);
        assertEquals(
                Member.of(memberId, createdMember.lastname, createdMember.firstname, new EmailAddress(createdMember.emailAddress.getValue()), createdMember.password, createdMember.subscription),
                storedMember
        );
    }
}
