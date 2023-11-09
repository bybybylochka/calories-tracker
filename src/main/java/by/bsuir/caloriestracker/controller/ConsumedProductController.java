package by.bsuir.caloriestracker.controller;

import by.bsuir.caloriestracker.models.ConsumedProduct;
import by.bsuir.caloriestracker.request.ConsumedProductRequest;
import by.bsuir.caloriestracker.response.ConsumedProductHistoryResponse;
import by.bsuir.caloriestracker.response.ConsumedProductResponse;
import by.bsuir.caloriestracker.service.ConsumedProductService;
import by.bsuir.caloriestracker.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;

@RestController
@RequiredArgsConstructor
@RequestMapping("/consumed-product")
public class ConsumedProductController {
    private final ConsumedProductService consumedProductService;
    @PostMapping("/add")
    public ConsumedProduct addConsumedProduct(@RequestBody ConsumedProductRequest request){
        return consumedProductService.addConsumedProduct(request);
    }

    @GetMapping
    public ConsumedProductResponse getAllConsumedProducts(){
        return consumedProductService.findAll();
    }

    @GetMapping("/today")
    public ConsumedProductResponse getConsumedProductsToday(){
        return consumedProductService.findConsumedProductByDate(LocalDate.now());
    }
    @GetMapping("/user")
    public ConsumedProductHistoryResponse getHistory(){
        return consumedProductService.findConsumedProductHistory();
    }
}
