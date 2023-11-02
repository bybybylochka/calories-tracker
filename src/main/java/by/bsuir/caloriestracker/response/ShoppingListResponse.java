package by.bsuir.caloriestracker.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.util.Map;

@Getter
@AllArgsConstructor
public class ShoppingListResponse {
    private Map<String, Integer> response;
}
