package by.bsuir.caloriestracker.controller;

import by.bsuir.caloriestracker.dto.PersonalDataDto;
import by.bsuir.caloriestracker.models.PersonalData;
import by.bsuir.caloriestracker.request.PersonalDataEditingRequest;
import by.bsuir.caloriestracker.request.PersonalDataRequest;
import by.bsuir.caloriestracker.service.PersonalDataService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/personalData")
@CrossOrigin
public class PersonalDataController {
    private final PersonalDataService personalDataService;
    @PostMapping("/add")
    public PersonalData addPersonalData(@RequestBody PersonalDataRequest request) {
        return personalDataService.addPersonalData(request);
    }
    @GetMapping
    public PersonalDataDto getPersonalData(){
        return personalDataService.getPersonalData();
    }
    @PutMapping("/update-personal-data")
    public PersonalDataDto editPersonalData(@RequestBody PersonalDataEditingRequest request) {
        return personalDataService.editPersonalData(request);
    }
    @PutMapping("/update-weight/{weight}")
    public PersonalData editWeight(@PathVariable float weight) {
        return personalDataService.editCurrentWeight(weight);
    }
}
