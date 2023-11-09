package by.bsuir.caloriestracker.controller;

import by.bsuir.caloriestracker.response.WeightHistoryResponse;
import by.bsuir.caloriestracker.service.WeightHistoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/weight-history")
public class WeightHistoryController {
    private final WeightHistoryService weightHistoryService;
    @GetMapping("/user")
    public WeightHistoryResponse getWeightHistoryByUser(){
        return weightHistoryService.getWeightHistoryByUser();
    }
}
