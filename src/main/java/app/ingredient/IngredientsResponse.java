package app.ingredient;

import app.shared.AbstractResponse;

import java.util.List;

public class IngredientsResponse extends AbstractResponse {

    List<Ingredient> ingredients;

    public List<Ingredient> getIngredients() {
        return ingredients;
    }

    public void setIngredients(List<Ingredient> ingredients) {
        this.ingredients = ingredients;
    }
}
