package by.bsuir.caloriestracker.request;

import lombok.Getter;

import java.util.List;

@Getter
public class ShoppingListRequest {
    private List<Long> recipes;
}
