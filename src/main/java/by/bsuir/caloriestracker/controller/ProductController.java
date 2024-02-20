package by.bsuir.caloriestracker.controller;

import by.bsuir.caloriestracker.dto.ProductDto;
import by.bsuir.caloriestracker.models.Product;
import by.bsuir.caloriestracker.request.ProductRequest;
import by.bsuir.caloriestracker.response.ProductResponse;
import by.bsuir.caloriestracker.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/product")
public class ProductController {
    private final ProductService productService;

    @PostMapping("/add")
    public Product addProduct(@RequestBody ProductRequest request){
        return productService.addProduct(request);
    }

    @GetMapping("")
    public ProductResponse getAllProducts(){
        return productService.findAll();
    }

    @GetMapping("/allByName")
    public List<ProductDto> getAllProductsByName(@RequestParam String name){
        return productService.findListByName(name);
    }
}
