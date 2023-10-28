package by.bsuir.caloriestracker.models.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum Gender {
    MALE(" male"),
    FEMALE(" female"),
    OTHER(" other");

    private final String description;

    public static Gender getGender (String description){
        for(Gender type : Gender.values()) {
            if(type.description.equals(description)){
                return type;
            }
        }
        throw new IllegalArgumentException("Gender with this description not found");
    }


}
