package by.bsuir.caloriestracker.service;

import by.bsuir.caloriestracker.models.PersonalData;
import by.bsuir.caloriestracker.models.User;
import by.bsuir.caloriestracker.repository.UserRepository;
import by.bsuir.caloriestracker.response.UserResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;

    public User findById(long userId) {
        return userRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("User with this id not found"));
    }

    public UserResponse findAll(){
        return new UserResponse(userRepository.findAll());
    }

    public PersonalData findPersonalData(long userId) {
        return findById(userId).getPersonalData();
    }

    public void addPersonalData(long userId, PersonalData personalData) {
        User user = findById(userId);
        user.setPersonalData(personalData);
        userRepository.save(user);
    }
}
