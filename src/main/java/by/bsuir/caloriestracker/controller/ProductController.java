package by.bsuir.caloriestracker.controller;

import by.bsuir.caloriestracker.models.Product;
import by.bsuir.caloriestracker.request.ProductRequest;
import by.bsuir.caloriestracker.response.ProductResponse;
import by.bsuir.caloriestracker.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/product")
public class ProductController {
    private final ProductService productService;

    @PostMapping("/add")
    public Product addProduct(ProductRequest request){
        return productService.addProduct(request);
    }

    @GetMapping("")
    public ProductResponse getAllProducts(){
        return productService.findAll();
    }
}
