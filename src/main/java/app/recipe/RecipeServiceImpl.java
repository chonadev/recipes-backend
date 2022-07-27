package app.recipe;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;

import java.util.Arrays;

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


}
