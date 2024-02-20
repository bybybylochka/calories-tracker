package by.bsuir.caloriestracker.models.builder;

import by.bsuir.caloriestracker.models.Kbju;
import by.bsuir.caloriestracker.models.Norm;
import by.bsuir.caloriestracker.models.enums.GoalType;
import by.bsuir.caloriestracker.models.enums.MealType;

public class NormBuilder {
    private GoalType goalType;
    private double basalMetabolism;
    private int caloriesNorm;
    private int carbsNorm;
    private int proteinsNorm;
    private int fatsNorm;

    public NormBuilder forGoalType(GoalType goalType) {
        this.goalType = goalType;
        return this;
    }

    public NormBuilder withBasalMetabolism(double basalMetabolism) {
        this.basalMetabolism = basalMetabolism;
        return this;
    }

    public NormBuilder calculateCaloriesNorm() {
        this.caloriesNorm = (int) (this.basalMetabolism * goalType.getMetabolismCoefficient());
        return this;
    }

    public NormBuilder andProteins() {
        this.proteinsNorm = (int) (this.caloriesNorm * goalType.getProteinsCoefficient() / 4);
        return this;
    }

    public NormBuilder andFats() {
        this.fatsNorm = (int) (this.caloriesNorm * goalType.getFatsCoefficient() / 4.1);
        return this;
    }

    public NormBuilder andCarbs() {
        this.carbsNorm = (int) (this.caloriesNorm * goalType.getCarbsCoefficient() / 9);
        return this;
    }

    public Norm buildNorm() {
        Kbju kbju = new Kbju(this.caloriesNorm, this.proteinsNorm, this.carbsNorm, this.fatsNorm);
        return Norm.builder()
                .kbju(kbju)
                .breakfastNorm((int) (MealType.BREAKFAST.getPercentageInDailyMeal() * caloriesNorm))
                .lunchNorm((int) (MealType.LUNCH.getPercentageInDailyMeal() * caloriesNorm))
                .dinnerNorm((int) (MealType.DINNER.getPercentageInDailyMeal() * caloriesNorm))
                .snackNorm((int) (MealType.SNACK.getPercentageInDailyMeal() * caloriesNorm))
                .build();
    }
}
