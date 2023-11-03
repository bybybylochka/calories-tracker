package by.bsuir.caloriestracker.controller;

import by.bsuir.caloriestracker.request.ShoppingListRequest;
import by.bsuir.caloriestracker.response.ShoppingListResponse;
import by.bsuir.caloriestracker.service.ShoppingListService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/shopping-list")
public class ShoppingListController {
    private final ShoppingListService shoppingListService;

    @PostMapping("/create")
    public ShoppingListResponse createShoppingList(@RequestBody ShoppingListRequest request){
        return shoppingListService.createShoppingList(request);
    }
}
