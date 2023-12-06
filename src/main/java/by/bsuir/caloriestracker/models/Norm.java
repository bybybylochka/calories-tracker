package by.bsuir.caloriestracker.models;

import jakarta.persistence.Embedded;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@Getter
@NoArgsConstructor
@Builder
public class Norm {
    @Embedded
    private Kbju kbju;
    private int breakfastNorm;
    private int lunchNorm;
    private int dinnerNorm;
    private int snackNorm;
}
