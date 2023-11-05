package by.bsuir.caloriestracker.repository;

import by.bsuir.caloriestracker.models.ConsumedProduct;
import by.bsuir.caloriestracker.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ConsumedProductRepository extends JpaRepository<ConsumedProduct, Long> {
    List<ConsumedProduct> findAllByUser(User user);
}
