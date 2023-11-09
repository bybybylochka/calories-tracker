package by.bsuir.caloriestracker.service;

import by.bsuir.caloriestracker.models.Kbju;
import by.bsuir.caloriestracker.models.PersonalData;
import by.bsuir.caloriestracker.models.WeightHistory;
import by.bsuir.caloriestracker.models.builder.KbjuBuilder;
import by.bsuir.caloriestracker.models.enums.ActivityType;
import by.bsuir.caloriestracker.models.enums.Gender;
import by.bsuir.caloriestracker.models.enums.GoalType;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.Period;
import java.util.List;

@Component
@RequiredArgsConstructor
public class CaloriesCalculationService {

    public Kbju calculateNorm(PersonalData personalData) {
        double basalMetabolismWithoutActivity = calculateBasalMetabolism(personalData);
        double activityCoefficient = personalData.getActivityType().getActivityCoefficient();
        return new KbjuBuilder()
                .forGoalType(personalData.getGoalType())
                .withBasalMetabolism(basalMetabolismWithoutActivity * activityCoefficient)
                .calculateCaloriesNorm()
                .andCarbs().andFats().andProteins()
                .buildKbju();
    }

    private double calculateBasalMetabolism(PersonalData personalData) {
        List<WeightHistory> weightHistoryList = personalData.getWeightHistory();
        int genderCoefficient;
        if (personalData.getGender() == Gender.MALE) genderCoefficient = 5;
        else genderCoefficient = -161;
        int age = Period.between(personalData.getDateOfBirth(), LocalDate.now()).getYears();
        return 10 * (weightHistoryList.get(weightHistoryList.size() - 1).getWeight()) +
                6.25 * (personalData.getHeight()) -
                (5 * age) + genderCoefficient;
    }

    public void calculateTimeOfAchievements(PersonalData personalData) {

    }
}
