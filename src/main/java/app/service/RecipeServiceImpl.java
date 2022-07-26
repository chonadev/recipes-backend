package app.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import app.datasource.RecipeDatasource;

public class RecipeServiceImpl implements RecipeService {

	private static final Logger logger = LoggerFactory.getLogger(RecipeServiceImpl.class);
	
	@Autowired
	private RecipeDatasource datasource;
	
	@Override
	public RecipesResponse getRecipes() {
		
		RecipesResponse response = new RecipesResponse();
		response.setCode("200");
		response.setDescription("ok");
		
		try{
			response.getRecipes().addAll(datasource.findAll());
		} catch (Exception e) {
			logger.error("Error al obtener las recetas de la base de datos.");
			response.setCode("500");
			response.setDescription("Ha ocurrido un error al consultar las recetas");
		}
		
		return response;
	}


	

}
