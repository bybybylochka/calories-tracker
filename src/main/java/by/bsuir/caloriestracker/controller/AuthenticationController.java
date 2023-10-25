package by.bsuir.caloriestracker.controller;

import by.bsuir.caloriestracker.auth.AuthenticationService;
import by.bsuir.caloriestracker.request.AuthenticationRequest;
import by.bsuir.caloriestracker.response.AuthenticationResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class AuthenticationController {
    private AuthenticationService authenticationService;
    @PostMapping("/register")
    public AuthenticationResponse register(@RequestBody AuthenticationRequest request) {
        return authenticationService.register(request);
    }

    @PostMapping("/authorize")
    public AuthenticationResponse authorize(@RequestBody AuthenticationRequest request){
        return authenticationService.authorize(request);
    }
}
