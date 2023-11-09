package by.bsuir.caloriestracker.repository;

import by.bsuir.caloriestracker.models.PersonalData;
import by.bsuir.caloriestracker.models.WeightHistory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface WeightHistoryRepository extends JpaRepository<WeightHistory, Long> {
    List<WeightHistory> getWeightHistoryByPersonalData(PersonalData personalData);
}
