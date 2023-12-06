package by.bsuir.caloriestracker.service;

import by.bsuir.caloriestracker.dto.PersonalDataDto;
import by.bsuir.caloriestracker.models.Norm;
import by.bsuir.caloriestracker.models.PersonalData;
import by.bsuir.caloriestracker.models.User;
import by.bsuir.caloriestracker.models.WeightHistory;
import by.bsuir.caloriestracker.models.enums.ActivityType;
import by.bsuir.caloriestracker.models.enums.Gender;
import by.bsuir.caloriestracker.models.enums.GoalType;
import by.bsuir.caloriestracker.repository.PersonalDataRepository;
import by.bsuir.caloriestracker.request.PersonalDataEditingRequest;
import by.bsuir.caloriestracker.request.PersonalDataRequest;
import by.bsuir.caloriestracker.response.EnumResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

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

    public PersonalDataDto getPersonalData() {
        User user = userService.getCurrentUser();
        PersonalDataDto personalDataDto = toDto(userService.findPersonalData(user));
        return personalDataDto;
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



    public PersonalData addPersonalData(PersonalDataRequest personalDataRequest) {
        List<WeightHistory> weightHistoryList = new ArrayList<>();
        WeightHistory weightHistory = buildWeightHistory(personalDataRequest.getWeight());
        weightHistoryList.add(weightHistory);
        PersonalData personalData = buildPersonalData(personalDataRequest, weightHistoryList);
        PersonalData savedPersonalData = personalDataRepository.save(personalData);
        WeightHistory finalWeightHistory = weightHistory.toBuilder().personalData(savedPersonalData).build();
        weightHistoryService.addWeightInHistory(finalWeightHistory);
        userService.addPersonalData(savedPersonalData);
        return savedPersonalData;
    }

    public PersonalData editPersonalData(PersonalDataEditingRequest request) {
        PersonalData personalData = userService.getCurrentUser().getPersonalData();
        PersonalData editedPersonalData = buildEditedPersonalData(personalData, request);
        Norm norm = caloriesCalculationService.calculateNorm(editedPersonalData);
        editedPersonalData.setNorm(norm);
        return personalDataRepository.save(editedPersonalData);
    }

    public PersonalData editCurrentWeight(float currentWeight) {
        PersonalData personalData = userService.getCurrentUser().getPersonalData();
        WeightHistory weightHistory = buildWeightHistory(currentWeight);
        WeightHistory finalWeightHistory = weightHistory.toBuilder().personalData(personalData).build();
        weightHistoryService.addWeightInHistory(finalWeightHistory);
        return personalData;
    }

    private WeightHistory buildWeightHistory(float weight){
        return WeightHistory.builder()
                .weightInDate(LocalDate.now())
                .weight(weight)
                .build();
    }

    private PersonalData buildPersonalData(PersonalDataRequest request, List<WeightHistory> weightHistoryList){
        PersonalData personalData = PersonalData.builder()
                .name(request.getName())
                .gender(Gender.getGender(request.getGender()))
                .activityType(ActivityType.getActivityType(request.getActivityType()))
                .goalType(GoalType.getGoalType(request.getGoalType()))
                .height(request.getHeight())
                .weightHistory(weightHistoryList)
                .desiredWeight(request.getDesiredWeight())
                .dateOfBirth(LocalDate.parse(request.getDateOfBirth(), DateTimeFormatter.ofPattern("dd-MM-yyyy")))
                .build();
        personalData = personalData.toBuilder().norm(caloriesCalculationService.calculateNorm(personalData)).build();
        return personalData;
    }

    private PersonalData buildEditedPersonalData(PersonalData personalData, PersonalDataEditingRequest request) {
        return personalData.toBuilder()
                .activityType(ActivityType.getActivityType(request.getActivityType()))
                .goalType(GoalType.getGoalType(request.getGoalType()))
                .desiredWeight(request.getDesiredWeight())
                .height(request.getHeight())
                .build();
    }

    private PersonalDataDto toDto(PersonalData personalData) {
        return PersonalDataDto.builder()
                .id(personalData.getId())
                .name(personalData.getName())
                .activityType(personalData.getActivityType().getDescription())
                .gender(personalData.getGender().getDescription())
                .goalType(personalData.getGoalType().getDescription())
                .height(personalData.getHeight())
                .dateOfBirth(personalData.getDateOfBirth())
                .desiredWeight(personalData.getDesiredWeight())
                .norm(personalData.getNorm())
                .weightHistory(mapWeightHistory(personalData.getWeightHistory()))
                .build();
    }

    private Map<LocalDate, Float> mapWeightHistory(List<WeightHistory> weightHistoryList) {
        return weightHistoryList.stream()
                .collect(Collectors.toMap(WeightHistory::getWeightInDate, WeightHistory::getWeight));
    }




}
