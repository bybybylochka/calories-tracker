package by.bsuir.caloriestracker.controller;

import by.bsuir.caloriestracker.models.ProductDetails;
import by.bsuir.caloriestracker.service.ProductDetailsService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
@RequiredArgsConstructor
@RequestMapping("/product-details")
public class ProductDetailsController {
    private final ProductDetailsService productDetailsService;
    @GetMapping("/{query}")
    public ProductDetails getProductDetails(@PathVariable String query) throws IOException, InterruptedException {
        return productDetailsService.getProductDetails(query);
    }
}
