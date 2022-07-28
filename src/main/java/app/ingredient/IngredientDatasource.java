package app.ingredient;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IngredientDatasource extends JpaRepository<Ingredient, Long> {


    @Query(value = "SELECT i.name, SUM(i.amount) FROM Ingredient i WHERE i.name = i.name GROUP BY i.name", nativeQuery = true)
    List<Object[]> getIngredientsWithOutRepeat();
}
