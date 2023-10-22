package by.bsuir.caloriestracker.repository;

import by.bsuir.caloriestracker.models.Admin;
import by.bsuir.caloriestracker.models.Editor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface EditorRepository extends JpaRepository<Editor, Long> {
    @Query("SELECT e FROM Editor e INNER JOIN AuthorizationData a " +
            "ON e.authorizationData.id = a.id " +
            "WHERE a.login = :username")
    Optional<Editor> findByUsername(@Param("username") String username);
}
