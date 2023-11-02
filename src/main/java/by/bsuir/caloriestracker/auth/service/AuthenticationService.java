package by.bsuir.caloriestracker.auth.service;

import by.bsuir.caloriestracker.auth.JWTUtils;
import by.bsuir.caloriestracker.request.AuthenticationRequest;
import by.bsuir.caloriestracker.response.AuthenticationResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class AuthenticationService {
    private final AuthenticationManager authenticationManager;
    private final AppUserService appUserService;
    private final JWTUtils jwtUtils;


    public AuthenticationResponse authorize(AuthenticationRequest authenticationRequest) {
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(
                authenticationRequest.getUsername(), authenticationRequest.getPassword()
        );
        authenticationManager.authenticate(authenticationToken);
        UserDetails user = appUserService.loadUserByUsername(authenticationRequest.getUsername());
        String token = jwtUtils.generateToken(user);
        return new AuthenticationResponse(token);
    };

}
