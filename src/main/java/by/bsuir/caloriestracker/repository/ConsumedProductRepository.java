package by.bsuir.caloriestracker.repository;

import by.bsuir.caloriestracker.models.ConsumedProduct;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ConsumedProductRepository extends JpaRepository<ConsumedProduct, Long> {

}
