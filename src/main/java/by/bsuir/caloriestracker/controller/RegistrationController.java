package by.bsuir.caloriestracker.controller;

import by.bsuir.caloriestracker.models.Editor;
import by.bsuir.caloriestracker.request.EditorRegistrationRequest;
import by.bsuir.caloriestracker.request.UserAuthenticationRequest;
import by.bsuir.caloriestracker.response.AuthenticationResponse;
import by.bsuir.caloriestracker.service.RegistrationService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/register")
public class RegistrationController {
    private final RegistrationService registrationService;
    @PostMapping("/user")
    public AuthenticationResponse register(@RequestBody UserAuthenticationRequest request) {
        return registrationService.register(request);
    }

    @PostMapping("/editor")
    public Editor register(@RequestBody EditorRegistrationRequest request) {
        return registrationService.register(request);
    }
}
