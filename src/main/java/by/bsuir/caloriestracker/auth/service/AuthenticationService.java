package by.bsuir.caloriestracker.auth.service;

import by.bsuir.caloriestracker.auth.JWTUtils;
import by.bsuir.caloriestracker.request.UserAuthenticationRequest;
import by.bsuir.caloriestracker.response.AuthenticationResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class AuthenticationService {
    private final AuthenticationManager authenticationManager;
    private final AppUserService appUserService;
    private final JWTUtils jwtUtils;


    public AuthenticationResponse authorize(UserAuthenticationRequest authenticationRequest) {
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(
                authenticationRequest.getUsername(), authenticationRequest.getPassword()
        );
        authenticationManager.authenticate(authenticationToken);
        UserDetails user = appUserService.loadUserByUsername(authenticationRequest.getUsername());
        String token = jwtUtils.generateToken(user);
        GrantedAuthority grantedAuthority = user.getAuthorities().stream().findFirst().orElseThrow();
        String role = grantedAuthority.getAuthority();
        return new AuthenticationResponse(token, role);
    };

    public void logout() {
        SecurityContextHolder.getContext().setAuthentication(null);
    }

}
