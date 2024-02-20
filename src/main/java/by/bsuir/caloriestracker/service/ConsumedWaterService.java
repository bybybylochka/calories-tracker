package by.bsuir.caloriestracker.service;

import by.bsuir.caloriestracker.dto.ConsumedWaterDto;
import by.bsuir.caloriestracker.models.ConsumedWater;
import by.bsuir.caloriestracker.models.User;
import by.bsuir.caloriestracker.repository.ConsumedWaterRepository;
import by.bsuir.caloriestracker.response.ConsumedWaterHistoryResponse;
import by.bsuir.caloriestracker.response.ConsumedWaterResponse;
import jakarta.persistence.criteria.CriteriaBuilder;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
@RequiredArgsConstructor
public class ConsumedWaterService {
    private final ConsumedWaterRepository consumedWaterRepository;
    private final UserService userService;

    public ConsumedWater findById(long consumedWaterId) {
        return consumedWaterRepository.findById(consumedWaterId)
                .orElseThrow(() -> new IllegalArgumentException("Consumed water with this id not found"));
    }

    public ConsumedWaterResponse findAll() {
        List<ConsumedWater> consumedWaterList = consumedWaterRepository.findAll();
        List<ConsumedWaterDto> dtoList = consumedWaterList.stream().map(this::toDto).toList();
        int volume = dtoList.stream()
                .mapToInt(ConsumedWaterDto::getVolume)
                .reduce(0, Integer::sum);
        return new ConsumedWaterResponse(dtoList, volume);
    }

//    public ConsumedWaterResponse getConsumedWaterByUser() {
//        List<ConsumedWater> allByUser = findConsumedWaterByUser();
//        List<ConsumedWaterDto> dtoList = allByUser.stream().map(this::toDto).toList();
//        return new ConsumedWaterResponse(dtoList);
//    }

    public List<ConsumedWater> findConsumedWaterByUser() {
        User currentUser = userService.getCurrentUser();
        return consumedWaterRepository.findAllByUser(currentUser);
    }

    public ConsumedWaterDto addConsumedWater(int volume) {
        ConsumedWater consumedWater = buildConsumedWater(volume);
        ConsumedWater savedConsumedWater = consumedWaterRepository.save(consumedWater);
        return toDto(savedConsumedWater);
    }

    public ConsumedWaterResponse findConsumedWaterByDate(LocalDate date) {
        List<ConsumedWater> consumedWaterList = findConsumedWaterByUser();
        List<ConsumedWater> consumedWaterByDate = consumedWaterList.stream().filter(consumedWater ->
                consumedWater.getConsumptionTime().getDayOfYear() == date.getDayOfYear()
        ).toList();
        List<ConsumedWaterDto> dtoList = consumedWaterByDate.stream().map(this::toDto).toList();
        int volume = dtoList.stream()
                .mapToInt(ConsumedWaterDto::getVolume)
                .reduce(0, Integer::sum);
        return new ConsumedWaterResponse(dtoList, volume);
    }

    public ConsumedWaterHistoryResponse findConsumedWaterHistory() {
        final int daysQuantity = 14;
        LocalDate startDate = LocalDate.now().minusDays(daysQuantity);
        Map<LocalDate, ConsumedWaterResponse> consumptionHistory = new HashMap<>();
        for (int i = 0; i < daysQuantity; i++) {
            LocalDate currentDate = startDate.plusDays(i);
            consumptionHistory.put(currentDate, findConsumedWaterByDate(currentDate));
        }
        return new ConsumedWaterHistoryResponse(consumptionHistory);
    }

    public void updateVolume(long consumedWaterId, int volume) {
        consumedWaterRepository.updateConsumedWater(consumedWaterId, volume);
    }

    private ConsumedWater buildConsumedWater(int volume) {
        return ConsumedWater.builder()
                .consumptionTime(LocalDateTime.now())
                .volume(volume)
                .user(userService.getCurrentUser())
                .build();
    }

    private ConsumedWaterDto toDto(ConsumedWater consumedWater) {
        return ConsumedWaterDto.builder()
                .id(consumedWater.getId())
                .volume(consumedWater.getVolume())
                .consumptionTime(DateTimeFormatter.ISO_LOCAL_DATE_TIME.format(consumedWater.getConsumptionTime()))
                .userId(consumedWater.getUser().getId())
                .build();
    }

    public ConsumedWaterDto deleteConsumedWater(int volume) {
        ConsumedWater consumedWater = buildConsumedWater(volume * (-1));
        ConsumedWater savedConsumedWater = consumedWaterRepository.save(consumedWater);
        return toDto(savedConsumedWater);
    }
}
