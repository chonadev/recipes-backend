package app.ingredient;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;

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
            response.setIngredients(datasource.findAll());
        } catch (Exception e){
            logger.error("Error al obtener los ingredientes de la base de datos.");
            response.setCode(HttpStatus.INTERNAL_SERVER_ERROR.toString());
            response.setDescription("Ha ocurrido un error al consultar los ingredientes");
        }
        return response;
    }
}
