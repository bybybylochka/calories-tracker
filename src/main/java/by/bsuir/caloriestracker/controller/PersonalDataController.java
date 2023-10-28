package by.bsuir.caloriestracker.controller;

import by.bsuir.caloriestracker.models.PersonalData;
import by.bsuir.caloriestracker.request.PersonalDataRequest;
import by.bsuir.caloriestracker.service.PersonalDataService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/personalData")
public class PersonalDataController {
    private final PersonalDataService personalDataService;
    @PostMapping("/add/{userId}")
    public PersonalData addPersonalData(@PathVariable long userId, @RequestBody PersonalDataRequest request) {
        return personalDataService.addPersonalData(userId, request);
    }
}
