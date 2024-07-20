package use_cases.recipe_to_grocery.use_case.output_data;

import entity.Ingredient;
import org.jetbrains.annotations.NotNull;

import java.util.Iterator;
import java.util.List;

public class RecipeToGroceryOutputData implements Iterable<Ingredient> {
    private List<Ingredient> ingredients;

    public RecipeToGroceryOutputData(List<Ingredient> ingredients) {
        this.ingredients = ingredients;
    }
    public List<Ingredient> getRecipes() {
        return ingredients;
    }

    @NotNull
    @Override
    public Iterator<Ingredient> iterator() {
        return ingredients.iterator();
    }
}
