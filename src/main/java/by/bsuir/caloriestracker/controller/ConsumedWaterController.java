package by.bsuir.caloriestracker.controller;

import by.bsuir.caloriestracker.dto.ConsumedWaterDto;
import by.bsuir.caloriestracker.models.ConsumedWater;
import by.bsuir.caloriestracker.response.ConsumedWaterHistoryResponse;
import by.bsuir.caloriestracker.response.ConsumedWaterResponse;
import by.bsuir.caloriestracker.service.ConsumedWaterService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;

@RestController
@RequiredArgsConstructor
@RequestMapping("/consumed-water")
public class ConsumedWaterController {
    private final ConsumedWaterService consumedWaterService;

    @PostMapping
    public ConsumedWaterDto addConsumedWater(@RequestParam int volume) {
        return consumedWaterService.addConsumedWater(volume);
    }

    @GetMapping
    public ConsumedWaterResponse getAllConsumedWater() {
        return consumedWaterService.findAll();
    }

    @GetMapping("/user")
    public ConsumedWaterHistoryResponse getAllConsumedWaterByUser() {
        return consumedWaterService.findConsumedWaterHistory();
    }

    @GetMapping("/today")
    public ConsumedWaterResponse getCurrentDayConsumptionHistory() {
        return consumedWaterService.findConsumedWaterByDate(LocalDate.now());
    }

    @PutMapping("/{consumedWaterId}")
    public void updateConsumedWater(@PathVariable long consumedWaterId, @RequestParam int volume) {
        consumedWaterService.updateVolume(consumedWaterId, volume);
    }

    @DeleteMapping()
    public ConsumedWaterDto deleteConsumedWater(@RequestParam int volume) {
        return consumedWaterService.deleteConsumedWater(volume);
    }
}
