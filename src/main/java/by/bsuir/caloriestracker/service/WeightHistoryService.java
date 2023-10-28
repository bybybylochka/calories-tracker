package by.bsuir.caloriestracker.service;

import by.bsuir.caloriestracker.models.WeightHistory;
import by.bsuir.caloriestracker.repository.WeightHistoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class WeightHistoryService {
    private final WeightHistoryRepository weightHistoryRepository;

    public WeightHistory findById(long id){
        return weightHistoryRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Weight history with this id not found"));
    }
    public void addWeightInHistory(WeightHistory weightHistory){
        weightHistoryRepository.save(weightHistory);
    }
}
