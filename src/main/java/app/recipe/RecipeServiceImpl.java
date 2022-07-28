package app.recipe;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.Optional;

public class RecipeServiceImpl implements RecipeService {

	private static final Logger logger = LoggerFactory.getLogger(RecipeServiceImpl.class);
	
	@Autowired
	private RecipeDatasource datasource;
	
	@Override
	public RecipesResponse getRecipes() {
		
		RecipesResponse response = new RecipesResponse();
		response.setCode(HttpStatus.OK.toString());
		response.setDescription(HttpStatus.OK.name());
		
		try{
			response.getRecipes().addAll(datasource.findAll());
		} catch (Exception e) {
			logger.error("Error al obtener las recetas de la base de datos.");
			response.setCode(HttpStatus.INTERNAL_SERVER_ERROR.toString());
			response.setDescription("Ha ocurrido un error al consultar las recetas");
		}
		
		return response;
	}

	@Override
	public RecipesResponse saveNewRecipe(Recipe req) {
		RecipesResponse response = new RecipesResponse();

		response.setCode(HttpStatus.CREATED.toString());
		response.setDescription(HttpStatus.CREATED.name());

		try {
			Recipe returnRecipe = datasource.save(req);
			response.setRecipes(Arrays.asList(returnRecipe));
		} catch(Exception e) {
			logger.error("Error al crear receta en la base de datos.");
			response.setCode(HttpStatus.INTERNAL_SERVER_ERROR.toString());
			response.setDescription("Ha ocurrido un error al crear la receta");
		}

		return response;
	}

	@Override
	public RecipesResponse updateRecipe(RecipeRequest req, Long id) {

		RecipesResponse response = new RecipesResponse();

		response.setCode(HttpStatus.OK.toString());
		response.setDescription(HttpStatus.OK.name());

		try {
			Optional<Recipe> recipeOptional = Optional.ofNullable(datasource.findOne(id));
			if (!recipeOptional.isPresent()) {
				response.setCode(HttpStatus.NOT_FOUND.toString());
				response.setDescription(HttpStatus.NOT_FOUND.name());
				return response;
			}

			Recipe recipeToUpdate = this.mapper(req);
			recipeToUpdate.setId(id);

			Recipe updatedRecipe = datasource.save(recipeToUpdate);
			response.setRecipes(Arrays.asList(updatedRecipe));
		} catch(Exception e) {
			logger.error("Error al actualizar receta en la base de datos.");
			response.setCode(HttpStatus.INTERNAL_SERVER_ERROR.toString());
			response.setDescription("Ha ocurrido un error al actualizar la receta");
		}

		return response;
	}

	@Override
	public RecipesResponse deleteRecipe(Long id) {
		RecipesResponse response = new RecipesResponse();

		response.setCode(HttpStatus.NO_CONTENT.toString());
		response.setDescription(HttpStatus.NO_CONTENT.name());
		try {
			this.datasource.delete(id);
		} catch (Exception e) {
			logger.error("Error al eliminar receta en la base de datos.");
			response.setCode(HttpStatus.INTERNAL_SERVER_ERROR.toString());
			response.setDescription("Ha ocurrido un error al eliminar la receta");
		}
		return response;
	}

	private Recipe mapper(RecipeRequest request) {
		Recipe recipe = new Recipe();
		recipe.setName(request.getName());
		recipe.setDescription(request.getDescription());
		recipe.setImagePath(request.getImagePath());
		recipe.setIngredients(request.getIngredients());
		return recipe;
	}
}
