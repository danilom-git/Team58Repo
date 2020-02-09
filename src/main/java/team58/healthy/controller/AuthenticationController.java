package team58.healthy.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import team58.healthy.dto.PasswordChangeDTO;
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
@CrossOrigin
public class AuthenticationController {
    @Autowired
    private UserService userService;

    @GetMapping(value = "/getUser/token:{token}/type:{type}")
    @PreAuthorize("hasRole('PATIENT') or hasRole('DOCTOR') or hasRole('CLINIC_ADMIN')")
    public ResponseEntity<?> getUser(@PathVariable String token, @PathVariable String type)
    {
        return ResponseEntity.ok(userService.findUser(token,type));
    }


    @PostMapping(value = "/login")
    public ResponseEntity<TokenDTO> createAuthenticationToken(@RequestBody JwtAuthenticationRequest authenticationRequest) {
        TokenDTO tokenDTO = userService.createAuthenticationToken(authenticationRequest);
        if (tokenDTO == null)
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        return new ResponseEntity<>(tokenDTO, HttpStatus.OK);
    }

    @PutMapping(value = "/changePassword")
    @PreAuthorize("hasRole('PATIENT') or hasRole('DOCTOR') or hasRole('CLINIC_ADMIN') or hasRole('CLINIC_CENTER_ADMIN')")
    public ResponseEntity<String> changePassword(@RequestBody PasswordChangeDTO passwordChangeDTO) {
        return new ResponseEntity<>(userService.changePassword(passwordChangeDTO.getOldPassword(), passwordChangeDTO.getNewPassword()), HttpStatus.OK);
    }
}
