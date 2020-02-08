package team58.healthy.dto;

public class PasswordChangeDTO {
    private String oldPassword;
    private String newPassword;

    public PasswordChangeDTO() { }

    public PasswordChangeDTO(String oldPassword, String newPassword) {
        this.oldPassword = oldPassword;
        this.newPassword = newPassword;
    }

    public String getOldPassword() {
        return oldPassword;
    }

    public String getNewPassword() {
        return newPassword;
    }
}
