package by.bsuir.caloriestracker.service;

import by.bsuir.caloriestracker.models.AuthorizationData;
import by.bsuir.caloriestracker.repository.AuthorizationDataRepository;
import by.bsuir.caloriestracker.request.UserAuthenticationRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
@RequiredArgsConstructor
public class AuthorizationDataService {
    private final AuthorizationDataRepository authorizationDataRepository;
    private final PasswordEncoder passwordEncoder;

    public AuthorizationData addAuthorizationData(UserAuthenticationRequest request){
        AuthorizationData authorizationData = buildAuthorizationData(request);
        return authorizationDataRepository.save(authorizationData);
    }


    private AuthorizationData buildAuthorizationData(UserAuthenticationRequest request){
        return AuthorizationData.builder()
                .createdAt(LocalDate.now())
                .login(request.getUsername())
                .password(passwordEncoder.encode(request.getPassword()))
                .build();
    }
}
