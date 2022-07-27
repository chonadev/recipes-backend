package app.recipe;

import java.util.List;

import app.recipe.Recipe;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;


public interface RecipeDatasource {

	List<Recipe> findAll();

}
