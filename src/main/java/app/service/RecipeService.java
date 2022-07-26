package app.service;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

import org.springframework.stereotype.Component;


@Path("recipe")
@Component
@Consumes("application/json")
@Produces("application/json")
public interface RecipeService {
	
	@GET
	@Path("/all")
	public RecipesResponse getRecipes();


}
