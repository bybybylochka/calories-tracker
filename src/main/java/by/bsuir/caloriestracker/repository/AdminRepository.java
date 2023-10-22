package by.bsuir.caloriestracker.repository;

import by.bsuir.caloriestracker.models.Admin;
import by.bsuir.caloriestracker.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AdminRepository extends JpaRepository<Admin, Long> {
    @Query("SELECT ad FROM Admin ad INNER JOIN AuthorizationData a " +
            "ON ad.authorizationData.id = a.id " +
            "WHERE a.login = :username")
    Optional<Admin> findByUsername(@Param("username") String username);
}
