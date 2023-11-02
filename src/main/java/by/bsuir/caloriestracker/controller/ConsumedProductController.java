package by.bsuir.caloriestracker.controller;

import by.bsuir.caloriestracker.models.ConsumedProduct;
import by.bsuir.caloriestracker.request.ConsumedProductRequest;
import by.bsuir.caloriestracker.response.ConsumedProductResponse;
import by.bsuir.caloriestracker.service.ConsumedProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

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
}
