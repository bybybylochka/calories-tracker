package by.bsuir.caloriestracker.controller;

import by.bsuir.caloriestracker.auth.service.AuthenticationService;
import by.bsuir.caloriestracker.request.UserAuthenticationRequest;
import by.bsuir.caloriestracker.response.AuthenticationResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class AuthenticationController {
    private final AuthenticationService authenticationService;

    @PostMapping("/authorize")
    public AuthenticationResponse authorize(@RequestBody UserAuthenticationRequest request){
        return authenticationService.authorize(request);
    }
}
