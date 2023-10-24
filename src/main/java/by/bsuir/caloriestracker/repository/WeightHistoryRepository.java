package by.bsuir.caloriestracker.repository;

import by.bsuir.caloriestracker.models.WeightHistory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WeightHistoryRepository extends JpaRepository<WeightHistory, Long> {
}
