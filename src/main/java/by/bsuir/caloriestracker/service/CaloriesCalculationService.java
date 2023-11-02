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

import java.util.List;

@Component
@RequiredArgsConstructor
public class CaloriesCalculationService {
    private final PersonalDataService personalDataService;

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
        return 10 * (weightHistoryList.get(weightHistoryList.size() - 1).getWeight()) +
                6.25 * (personalData.getHeight()) -
                (5 * personalData.getAge()) +
                genderCoefficient;
    }

//    public double getActivityCoefficient(ActivityType type) {
//        double activityCoefficient = 0;
//        switch (type) {
//            case INACTIVE -> activityCoefficient = 1;
//            case LIGHT_PHYSICAL_ACTIVITY -> activityCoefficient = 1.3;
//            case AVERAGE_PHYSICAL_ACTIVITY -> activityCoefficient = 1.5;
//            case HIGH_PHYSICAL_ACTIVITY -> activityCoefficient = 1.7;
//        }
//        return activityCoefficient;
//    }

//    public double calculateNorm(double basalMetabolism, GoalType type) {
//        return switch (type) {
//            case LOSING_WEIGHT -> basalMetabolism * 0.85;
//            case MAINTAINING_WEIGHT -> basalMetabolism;
//            case MASS_GAIN -> basalMetabolism * 1.15;
//        };
//    }
//
//    public double proteinsNorm(double caloriesNorm, GoalType type) {
//        double norm = switch (type) {
//            case LOSING_WEIGHT -> caloriesNorm * 0.35;
//            case MAINTAINING_WEIGHT -> caloriesNorm * 0.3;
//            case MASS_GAIN -> caloriesNorm * 0.4;
//        };
//        return norm / 4;
//    }
//
//    public double carbsNorm(double caloriesNorm, GoalType type) {
//        double norm = switch (type) {
//            case LOSING_WEIGHT -> caloriesNorm * 0.35;
//            case MAINTAINING_WEIGHT -> caloriesNorm * 0.4;
//            case MASS_GAIN -> caloriesNorm * 0.45;
//        };
//        return norm / 9;
//    }
//
//    public double fatsNorm(double caloriesNorm, GoalType type) {
//        double norm = switch (type) {
//            case LOSING_WEIGHT -> caloriesNorm * 0.25;
//            case MAINTAINING_WEIGHT -> caloriesNorm * 0.3;
//            case MASS_GAIN -> caloriesNorm * 0.15;
//        };
//        return norm / 4.1;
//    }

    public void calculateTimeOfAchievements(PersonalData personalData) {

    }
}
