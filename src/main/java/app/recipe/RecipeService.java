package app.recipe;

import javax.validation.Valid;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestBody;


@Path("recipe")
@Component
@Produces(MediaType.APPLICATION_JSON)
public interface RecipeService {
	
	@GET
	@Path("/all")
	public RecipesResponse getRecipes();

	@POST
	@Path("")
	@Consumes(MediaType.APPLICATION_JSON)
	public RecipesResponse saveNewRecipe(@Valid @RequestBody Recipe recipe);

	@PUT
	@Path("/{id}")
	@Consumes(MediaType.APPLICATION_JSON)
	public RecipesResponse updateRecipe(@RequestBody RecipeRequest recipe, @PathParam("id") Long id);

	@DELETE
	@Path("/{id}")
	@Consumes(MediaType.APPLICATION_JSON)
	public RecipesResponse deleteRecipe(@PathParam("id") Long id);
}
