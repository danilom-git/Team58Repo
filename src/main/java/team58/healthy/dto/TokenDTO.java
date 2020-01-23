package team58.healthy.dto;

public class TokenDTO {
    private String token;
    private Long expiresIn;
    private String userType;

    public TokenDTO() {}

    public TokenDTO(String token, Long expiresIn, String userType) {
        this.token = token;
        this.expiresIn = expiresIn;
        this.userType = userType;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public Long getExpiresIn() {
        return expiresIn;
    }

    public void setExpiresIn(Long expiresIn) {
        this.expiresIn = expiresIn;
    }

    public String getUserType() {
        return userType;
    }

    public void setUserType(String userType) {
        this.userType = userType;
    }
}
