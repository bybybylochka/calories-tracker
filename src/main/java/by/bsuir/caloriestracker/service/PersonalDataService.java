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

    // TODO: сделать аналогичные геттеры для остальных енамов

    public PersonalData addPersonalData(long userId, PersonalDataRequest personalDataRequest) {
        List<WeightHistory> weightHistoryList = new ArrayList<>();
        WeightHistory weightHistory = WeightHistory.builder()
                .weightInDate(LocalDate.now())
                .weight(personalDataRequest.getWeight())
                .build();
        weightHistoryService.addWeightInHistory(weightHistory);
        // возмножно нужно будет потом добавить ссылку на PersonalData ???
        weightHistoryList.add(weightHistory);
        PersonalData personalData = PersonalData.builder()
                .name(personalDataRequest.getName())
                .gender(Gender.getGender(personalDataRequest.getGender()))
                .activityType(ActivityType.getActivityType(personalDataRequest.getActivityType()))
                .goalType(GoalType.getGoalType(personalDataRequest.getGoalType()))
                .height(personalDataRequest.getHeight())
                .weightHistory(weightHistoryList)
                .desiredWeight(personalDataRequest.getDesiredWeight())
                .build();
        return personalDataRepository.save(personalData);
    }
}
