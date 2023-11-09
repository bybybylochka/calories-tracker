package by.bsuir.caloriestracker.request;

import lombok.Getter;

@Getter
public class PersonalDataRequest {
    private String name;
    private String gender;
    private String activityType;
    private String goalType;
    private float weight;
    private float height;
    private String dateOfBirth;
    private float desiredWeight;
}
