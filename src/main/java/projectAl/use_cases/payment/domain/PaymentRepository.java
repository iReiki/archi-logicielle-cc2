package projectAl.use_cases.payment.domain;

import projectAl.kernel.Repository;
import projectAl.use_cases.member.domain.Member;
import projectAl.use_cases.member.domain.MemberId;

import java.util.List;

public interface PaymentRepository extends Repository<PaymentId, Payment> {

    boolean isEmpty();

    List<Payment> findAll();

    List<Payment> findByMemberId(MemberId memberId);

}
