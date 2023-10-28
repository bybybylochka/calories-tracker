package by.bsuir.caloriestracker.response;

import by.bsuir.caloriestracker.models.User;
import lombok.AllArgsConstructor;

import java.util.List;

@AllArgsConstructor
public class UserResponse {
    private List<User> users;
}
