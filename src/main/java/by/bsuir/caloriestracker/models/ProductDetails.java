package by.bsuir.caloriestracker.models;

import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonValue;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@Setter
@Getter
@NoArgsConstructor
public class ProductDetails implements Serializable {
    private String name;
    private double calories;
    private double serving_size_g;
    private double fat_total_g;
    private double protein_g;
    private double sodium_mg;
    private double potassium_mg;
    private double cholesterol_mg;
    private double carbohydrates_total_g;
    private double fiber_g;
    private double sugar_g;
}
