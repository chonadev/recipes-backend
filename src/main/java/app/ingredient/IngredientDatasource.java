package app.ingredient;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IngredientDatasource extends JpaRepository<Ingredient, Long> {
}
