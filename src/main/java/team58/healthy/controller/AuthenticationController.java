package team58.healthy.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import team58.healthy.dto.TokenDTO;
import team58.healthy.model.ExtendedUserDetails;
import team58.healthy.security.TokenUtils;
import team58.healthy.security.auth.JwtAuthenticationRequest;
import team58.healthy.service.AuthorityService;
import team58.healthy.service.UserService;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@RestController
@RequestMapping(value = "auth")
public class AuthenticationController {

    @Autowired
    private TokenUtils tokenUtils;

    @Autowired
    private UserService userService;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private AuthorityService authorityService;

    @PostMapping(value = "/login")
    public ResponseEntity<?> createAuthenticationToken(@RequestBody JwtAuthenticationRequest authenticationRequest,
                                                       HttpServletResponse response) throws AuthenticationException, IOException {

        final Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                authenticationRequest.getUsername(), authenticationRequest.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(authentication);

        ExtendedUserDetails extendedUserDetails = (ExtendedUserDetails)authentication.getPrincipal();
        String jwt = tokenUtils.generateToken(extendedUserDetails.getUsername());
        Long expiresIn = tokenUtils.getExpiresIn();
        String userType = userService.findUserType(tokenUtils.getUsernameFromToken(jwt));

        return ResponseEntity.ok(new TokenDTO(jwt, expiresIn, userType));
    }

}
