package by.bsuir.caloriestracker.controller;

import by.bsuir.caloriestracker.auth.service.AuthenticationService;
import by.bsuir.caloriestracker.request.UserAuthenticationRequest;
import by.bsuir.caloriestracker.response.AuthenticationResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/authorize")
public class AuthenticationController {
    private final AuthenticationService authenticationService;
    @PostMapping
    public AuthenticationResponse authorize(@RequestBody UserAuthenticationRequest request){
        return authenticationService.authorize(request);
    }
    @GetMapping("/logout")
    public void logOut(){
        authenticationService.logout();
    }
}
