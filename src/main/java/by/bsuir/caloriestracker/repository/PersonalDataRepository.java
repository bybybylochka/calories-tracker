package by.bsuir.caloriestracker.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PersonalDataRepository extends JpaRepository<PersonalDataRepository, Long> {
}
