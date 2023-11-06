package by.bsuir.caloriestracker.service;

import by.bsuir.caloriestracker.models.AuthorizationData;
import by.bsuir.caloriestracker.repository.AuthorizationDataRepository;
import by.bsuir.caloriestracker.request.UserAuthenticationRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class AuthorizationDataService {
    private final AuthorizationDataRepository authorizationDataRepository;

    public AuthorizationData addAuthorizationData(UserAuthenticationRequest request){
        AuthorizationData authorizationData = buildAuthorizationData(request);
        return authorizationDataRepository.save(authorizationData);
    }

    private AuthorizationData buildAuthorizationData(UserAuthenticationRequest request){
        return AuthorizationData.builder()
                .login(request.getUsername())
                .password(request.getPassword())
                .build();
    }
}
