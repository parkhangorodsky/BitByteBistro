package use_case.output_data;

import entity.Recipe;
import org.jetbrains.annotations.NotNull;

import java.util.Iterator;
import java.util.List;

public class RecipeToGroceryOutputData implements Iterable<Recipe> {
    private List<Recipe> recipes;

    public RecipeToGroceryOutputData(List<Recipe> recipes) {
        this.recipes = recipes;
    }
    public List<Recipe> getRecipes() {
        return recipes;
    }

    @NotNull
    @Override
    public Iterator<Recipe> iterator() {
        return recipes.iterator();
    }
}
