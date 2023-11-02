package by.bsuir.caloriestracker.models.builder;

import by.bsuir.caloriestracker.models.Kbju;
import by.bsuir.caloriestracker.models.enums.GoalType;

public class KbjuBuilder {
    private GoalType goalType;
    private double basalMetabolism;
    private int caloriesNorm;
    private int carbsNorm;
    private int proteinsNorm;
    private int fatsNorm;

    public KbjuBuilder forGoalType(GoalType goalType) {
        this.goalType = goalType;
        return this;
    }

    public KbjuBuilder withBasalMetabolism(double basalMetabolism) {
        this.basalMetabolism = basalMetabolism;
        return this;
    }

    public KbjuBuilder calculateCaloriesNorm() {
        this.caloriesNorm = (int) (this.basalMetabolism * goalType.getMetabolismCoefficient());
        return this;
    }

    public KbjuBuilder andProteins() {
        this.proteinsNorm = (int) (this.caloriesNorm * goalType.getProteinsCoefficient() / 4);
        return this;
    }

    public KbjuBuilder andFats() {
        this.fatsNorm = (int) (this.caloriesNorm * goalType.getFatsCoefficient() / 4.1);
        return this;
    }

    public KbjuBuilder andCarbs() {
        this.carbsNorm = (int) (this.caloriesNorm * goalType.getCarbsCoefficient() / 9);
        return this;
    }

    public Kbju buildKbju() {
        return new Kbju(this.proteinsNorm, this.proteinsNorm, this.carbsNorm, this.fatsNorm);
    }
}
