package projectAl.use_cases.member.domain;

import projectAl.kernel.Repository;

import java.util.List;

public interface MemberRepository extends Repository<MemberId, Member> {

    boolean isEmpty();

    List<Member> findAll();

    List<Member> findBySubscription(String type);
}
