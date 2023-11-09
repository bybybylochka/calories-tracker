package by.bsuir.caloriestracker.controller;

import by.bsuir.caloriestracker.models.PersonalData;
import by.bsuir.caloriestracker.request.PersonalDataEditingRequest;
import by.bsuir.caloriestracker.request.PersonalDataRequest;
import by.bsuir.caloriestracker.service.PersonalDataService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/personalData")
public class PersonalDataController {
    private final PersonalDataService personalDataService;
    @PostMapping("/add")
    public PersonalData addPersonalData(@RequestBody PersonalDataRequest request) {
        return personalDataService.addPersonalData(request);
    }
    @PutMapping("/update-personal-data")
    public PersonalData editPersonalData(@RequestBody PersonalDataEditingRequest request) {
        return personalDataService.editPersonalData(request);
    }
    @PutMapping("/update-weight/{weight}")
    public PersonalData editWeight(@PathVariable float weight) {
        return personalDataService.editCurrentWeight(weight);
    }
}
