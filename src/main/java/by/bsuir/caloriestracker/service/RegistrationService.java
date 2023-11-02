package by.bsuir.caloriestracker.service;

import by.bsuir.caloriestracker.auth.JWTUtils;
import by.bsuir.caloriestracker.auth.service.AppUserService;
import by.bsuir.caloriestracker.models.AuthorizationData;
import by.bsuir.caloriestracker.models.User;
import by.bsuir.caloriestracker.repository.AuthorizationDataRepository;
import by.bsuir.caloriestracker.repository.UserRepository;
import by.bsuir.caloriestracker.request.AuthenticationRequest;
import by.bsuir.caloriestracker.response.AuthenticationResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class RegistrationService {
    private final AppUserService appUserService;
    private final JWTUtils jwtUtils;
    private final UserRepository userRepository;
    private final AuthorizationDataRepository authDataRepository;
    private final PasswordEncoder passwordEncoder;
    public AuthenticationResponse register(AuthenticationRequest authenticationRequest) {
        if(appUserService.loadUserByUsername(authenticationRequest.getUsername())==null){
            AuthorizationData authData = AuthorizationData.builder()
                    .login(authenticationRequest.getUsername())
                    .password(passwordEncoder.encode(authenticationRequest.getPassword()))
                    .build();
            authDataRepository.save(authData);
            User user = userRepository.save(User.builder().authorizationData(authData).build());
            String token = jwtUtils.generateToken(user);
            return new AuthenticationResponse(token);
        }
        else throw new IllegalArgumentException("user with this username already exists!");
    }
}
