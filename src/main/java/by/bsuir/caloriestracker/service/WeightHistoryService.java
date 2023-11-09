package by.bsuir.caloriestracker.service;

import by.bsuir.caloriestracker.models.PersonalData;
import by.bsuir.caloriestracker.models.User;
import by.bsuir.caloriestracker.models.WeightHistory;
import by.bsuir.caloriestracker.repository.WeightHistoryRepository;
import by.bsuir.caloriestracker.response.WeightHistoryResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class WeightHistoryService {
    private final WeightHistoryRepository weightHistoryRepository;
    private final UserService userService;


    public WeightHistory findById(long id){
        return weightHistoryRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Weight history with this id not found"));
    }
    public void addWeightInHistory(WeightHistory weightHistory){
        weightHistoryRepository.save(weightHistory);
    }

    public WeightHistoryResponse getWeightHistoryByUser(){
        User user = userService.getCurrentUser();
        PersonalData personalData = userService.findPersonalData(user);
        return new WeightHistoryResponse(weightHistoryRepository.getWeightHistoryByPersonalData(personalData));
    }
}
