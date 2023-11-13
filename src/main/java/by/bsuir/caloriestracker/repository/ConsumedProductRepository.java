package by.bsuir.caloriestracker.repository;

import by.bsuir.caloriestracker.models.ConsumedProduct;
import by.bsuir.caloriestracker.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ConsumedProductRepository extends JpaRepository<ConsumedProduct, Long> {
    List<ConsumedProduct> findAllByUser(User user);
    @Modifying
    @Query("update ConsumedProduct c set c.weight = :weight where c.id = :id")
    ConsumedProduct updateWeight(@Param("id") long id, @Param("weight") int weight);

}
