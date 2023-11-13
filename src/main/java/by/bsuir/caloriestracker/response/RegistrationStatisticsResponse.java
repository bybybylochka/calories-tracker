package by.bsuir.caloriestracker.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDate;
import java.util.Map;

@AllArgsConstructor
@Getter
public class RegistrationStatisticsResponse {
    private Map<LocalDate, Integer> registeredByDate;
}
