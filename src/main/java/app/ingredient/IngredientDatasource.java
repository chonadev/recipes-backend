package app.ingredient;

import org.springframework.data.jpa.repository.JpaRepository;

public interface IngredientDatasource extends JpaRepository<Ingredient, Long> {
}
