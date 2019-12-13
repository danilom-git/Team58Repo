package team58.healthy.security.auth;

import org.springframework.security.authentication.AbstractAuthenticationToken;
import team58.healthy.model.ExtendedUserDetails;

import javax.security.auth.Subject;

public class TokenBasedAuthentication extends AbstractAuthenticationToken {

    private String token;
    private final ExtendedUserDetails principle;

    public TokenBasedAuthentication(ExtendedUserDetails principle) {
        super(principle.getAuthorities());
        this.principle = principle;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    @Override
    public boolean isAuthenticated() {
        return true;
    }

    @Override
    public Object getCredentials() {
        return token;
    }

    @Override
    public Object getPrincipal() {
        return principle;
    }
}
