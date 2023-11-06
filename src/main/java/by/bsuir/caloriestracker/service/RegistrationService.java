package by.bsuir.caloriestracker.service;

import by.bsuir.caloriestracker.auth.JWTUtils;
import by.bsuir.caloriestracker.auth.service.AppUserService;
import by.bsuir.caloriestracker.models.*;
import by.bsuir.caloriestracker.repository.AuthorizationDataRepository;
import by.bsuir.caloriestracker.repository.EditorRepository;
import by.bsuir.caloriestracker.repository.UserRepository;
import by.bsuir.caloriestracker.request.EditorRegistrationRequest;
import by.bsuir.caloriestracker.request.UserAuthenticationRequest;
import by.bsuir.caloriestracker.response.AuthenticationResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@RequiredArgsConstructor
public class RegistrationService {
    private final AppUserService appUserService;
    private final JWTUtils jwtUtils;
    private final UserRepository userRepository;
    private final EditorRepository editorRepository;
    private final AuthorizationDataRepository authDataRepository;
    private final PasswordEncoder passwordEncoder;
    public AuthenticationResponse register(UserAuthenticationRequest authenticationRequest) {
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

    public Editor register(EditorRegistrationRequest registrationRequest) {
        if(appUserService.loadUserByUsername(registrationRequest.getUsername())==null){
            AuthorizationData authData = AuthorizationData.builder()
                    .login(registrationRequest.getUsername())
                    .password(passwordEncoder.encode(registrationRequest.getPassword()))
                    .build();
            authDataRepository.save(authData);
            return editorRepository.save(Editor.builder()
                    .authorizationData(authData)
                    .fullName(registrationRequest.getFullName())
                    .articles(new ArrayList<>())
                    .recipes(new ArrayList<>())
                    .build());
        }
        else throw new IllegalArgumentException("editor with this username already exists!");
    }
}
