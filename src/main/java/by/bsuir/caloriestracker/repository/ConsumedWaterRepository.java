package by.bsuir.caloriestracker.repository;

import by.bsuir.caloriestracker.models.ConsumedWater;
import by.bsuir.caloriestracker.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ConsumedWaterRepository extends JpaRepository<ConsumedWater, Long> {
    List<ConsumedWater> findAllByUser(User user);

    @Modifying
    @Query("update ConsumedWater cw set cw.volume = :volume where cw.id = :id")
    int updateConsumedWater(@Param("id") long id, @Param("volume") int volume);
}
