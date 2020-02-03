package team58.healthy.security.auth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.filter.OncePerRequestFilter;
import team58.healthy.model.ExtendedUserDetails;
import team58.healthy.security.TokenUtils;
import team58.healthy.service.UserService;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class TokenAuthenticationFilter extends OncePerRequestFilter {

    private TokenUtils tokenUtils;
    private UserService userService;

    public TokenAuthenticationFilter(TokenUtils tokenUtils, UserService userService) {
        this.tokenUtils = tokenUtils;
        this.userService = userService;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, FilterChain filterChain) throws ServletException, IOException {
        String username;
        String token = tokenUtils.getToken(httpServletRequest);

        if (token != null) {
            username = tokenUtils.getUsernameFromToken(token);

            if (username != null) {
                ExtendedUserDetails extendedUserDetails;
                try {
                    extendedUserDetails = userService.loadUserByUsername(username);
                } catch (UsernameNotFoundException e) {
                    httpServletResponse.sendError(HttpServletResponse.SC_UNAUTHORIZED, e.getMessage());
                    return;
                }

                if (tokenUtils.validateToken(token, extendedUserDetails)) {
                    TokenBasedAuthentication authentication = new TokenBasedAuthentication(extendedUserDetails);
                    authentication.setToken(token);
                    SecurityContextHolder.getContext().setAuthentication(authentication);
                }
            }
        }

        filterChain.doFilter(httpServletRequest, httpServletResponse);
    }
}
