package by.bsuir.caloriestracker.repository;

import by.bsuir.caloriestracker.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    @Query("SELECT u FROM User u INNER JOIN AuthorizationData a " +
            "ON u.authorizationData.id = a.id " +
            "WHERE a.login = :username")
    Optional<User> findByUsername(@Param("username") String username);
}
