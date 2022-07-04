package tech.surfer.examserver.controller;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import tech.surfer.examserver.auth.JwtUtils;
import tech.surfer.examserver.entity.JwtRequest;
import tech.surfer.examserver.entity.JwtResponse;

@RestController
@RequiredArgsConstructor
public class AuthenticationController {

    @NonNull
    private AuthenticationManager authenticationManager;

    @NonNull
    private UserDetailsService userDetailsService;

    @NonNull
    private JwtUtils jwtUtils;

    private void authenticate(String username, String password) {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
        } catch (DisabledException e) {
            throw new RuntimeException("USER DISABLED", e);
        } catch (BadCredentialsException e) {
            throw new RuntimeException("INVALID CREDENTIALS", e);
        }
    }

    @PostMapping("/generateToken")
    public ResponseEntity<JwtResponse> generateToken(@RequestBody JwtRequest jwtRequest) {
        try {
            authenticate(jwtRequest.getUserName(), jwtRequest.getPassword());
        } catch (UsernameNotFoundException e) {
            throw new RuntimeException("User Not Found", e);
        }

        // authenticated
        UserDetails userDetails = this.userDetailsService.loadUserByUsername(jwtRequest.getUserName());
        String token = this.jwtUtils.generateToken(userDetails);

        return ResponseEntity.ok(new JwtResponse(token));
    }
}
