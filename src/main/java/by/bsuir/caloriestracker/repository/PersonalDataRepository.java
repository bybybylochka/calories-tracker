package by.bsuir.caloriestracker.repository;

import by.bsuir.caloriestracker.models.PersonalData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PersonalDataRepository extends JpaRepository<PersonalData, Long> {
}
