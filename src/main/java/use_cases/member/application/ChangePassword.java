package use_cases.member.application;

public class ChangePassword {

    public final int memberId;
    public final String newPassword;

    public ChangePassword(int memberId, String newPassword) {
        this.memberId = memberId;
        this.newPassword = newPassword;
    }

}
