package by.bsuir.caloriestracker.service;

import by.bsuir.caloriestracker.models.PersonalData;
import by.bsuir.caloriestracker.models.WeightHistory;
import by.bsuir.caloriestracker.models.enums.ActivityType;
import by.bsuir.caloriestracker.models.enums.Gender;
import by.bsuir.caloriestracker.models.enums.GoalType;
import by.bsuir.caloriestracker.repository.PersonalDataRepository;
import by.bsuir.caloriestracker.request.PersonalDataRequest;
import by.bsuir.caloriestracker.response.EnumResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Component
@RequiredArgsConstructor
public class PersonalDataService {
    private final PersonalDataRepository personalDataRepository;
    private final WeightHistoryService weightHistoryService;
    private final UserService userService;
    private final CaloriesCalculationService caloriesCalculationService;

    public PersonalData findById(long id) {
        return personalDataRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Personal data with this id not found"));
    }

    public EnumResponse getActivityTypes() {
        List<String> descriptions=new ArrayList<>();
        for(ActivityType type: ActivityType.values()) {
            descriptions.add(type.getDescription());
        }
        return new EnumResponse(descriptions);
    }

    public EnumResponse getGender(){
        List<String> descriptions = new ArrayList<>();
        for(Gender gender: Gender.values()){
            descriptions.add(gender.getDescription());
        }
        return new EnumResponse(descriptions);
    }

    public EnumResponse getGoalType(){
        List<String> descriptions = new ArrayList<>();
        for(GoalType type: GoalType.values()){
            descriptions.add(type.getDescription());
        }
        return new EnumResponse(descriptions);
    }

    public PersonalData addPersonalData(long userId, PersonalDataRequest personalDataRequest) {
        List<WeightHistory> weightHistoryList = new ArrayList<>();
        WeightHistory weightHistory = buildWeightHistory(personalDataRequest);
        weightHistoryList.add(weightHistory);
        PersonalData personalData = buildPersonalData(personalDataRequest, weightHistoryList);
        weightHistory.toBuilder().personalData(personalData).build();
        weightHistoryService.addWeightInHistory(weightHistory);
        userService.addPersonalData(userId, personalData);
        return personalDataRepository.save(personalData);
    }

    private WeightHistory buildWeightHistory(PersonalDataRequest request){
        return WeightHistory.builder()
                .weightInDate(LocalDate.now())
                .weight(request.getWeight())
                .build();
    }

    private PersonalData buildPersonalData (PersonalDataRequest request, List<WeightHistory> weightHistoryList){
        PersonalData personalData = PersonalData.builder()
                .name(request.getName())
                .gender(Gender.getGender(request.getGender()))
                .activityType(ActivityType.getActivityType(request.getActivityType()))
                .goalType(GoalType.getGoalType(request.getGoalType()))
                .height(request.getHeight())
                .weightHistory(weightHistoryList)
                .desiredWeight(request.getDesiredWeight())
                .age(request.getAge())
                .build();
        personalData.toBuilder().norm(caloriesCalculationService.calculateNorm(personalData));
        return personalData;
    }

}
