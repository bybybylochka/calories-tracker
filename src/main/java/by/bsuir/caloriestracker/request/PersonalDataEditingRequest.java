package by.bsuir.caloriestracker.request;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class PersonalDataEditingRequest {
    private String name;
    private String activityType;
    private String goalType;
    private int height;
    //private int desiredWeight;
}
