package app.ingredient;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;

import java.util.ArrayList;

public class IngredientServiceImpl implements IngredientService {

    private static final Logger logger = LoggerFactory.getLogger(IngredientServiceImpl.class);

    @Autowired
    IngredientDatasource datasource;

    @Override
    public IngredientsResponse getIngredients() {
        IngredientsResponse response = new IngredientsResponse();

        response.setCode(HttpStatus.OK.toString());
        response.setDescription(HttpStatus.OK.name());

        try {
            if ( response.getIngredients() == null) response.setIngredients(new ArrayList<>());
            datasource.getIngredientsWithOutRepeat().forEach( row -> {
                Ingredient ingredient = new Ingredient();
                ingredient.setName(row[0].toString());
                ingredient.setAmount((Double) row[1]);
                response.getIngredients().add(ingredient);
            });
        } catch (Exception e){
            logger.error("Error al obtener los ingredientes de la base de datos.");
            response.setCode(HttpStatus.INTERNAL_SERVER_ERROR.toString());
            response.setDescription("Ha ocurrido un error al consultar los ingredientes");
        }
        return response;
    }
}
