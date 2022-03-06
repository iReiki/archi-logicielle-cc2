package projectAl.use_cases.member.application;

import projectAl.kernel.Query;
import projectAl.use_cases.member.domain.MemberId;

public class RetrieveMemberById implements Query {

    public final MemberId id;

    public RetrieveMemberById(final MemberId id) {
        this.id = id;
    }
}
