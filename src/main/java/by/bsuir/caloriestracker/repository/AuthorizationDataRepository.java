package by.bsuir.caloriestracker.repository;

import by.bsuir.caloriestracker.models.AuthorizationData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AuthorizationDataRepository extends JpaRepository<AuthorizationData, Long> {
}
