package team58.healthy.model;

import org.springframework.security.core.userdetails.UserDetails;

import java.util.Date;

public interface ExtendedUserDetails extends UserDetails {
    Date getLastPasswordResetDate();
    void setPassword(String password);
    String getEmail();
}
