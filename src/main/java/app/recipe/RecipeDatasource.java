package app.recipe;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RecipeDatasource extends JpaRepository<Recipe, Long> {
}
