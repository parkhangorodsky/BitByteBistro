package use_cases.search_recipe.use_case.output_data;

import entity.Recipe;
import org.jetbrains.annotations.NotNull;

import java.util.Iterator;
import java.util.List;

/**
 * Output data for the search recipe use case.
 * This class encapsulates the list of recipes found by the search in the iterable format.
 */
public class SearchRecipeOutputData implements Iterable<Recipe> {
    private List<Recipe> recipes; // The list of recipes in the search result.

    /**
     * Constructs a SearchRecipeOutputData object
     * with the specified list of recipes.
     *
     * @param recipes the list of recipes found by the search
     */
    public SearchRecipeOutputData(List<Recipe> recipes) {
        this.recipes = recipes;
    }

    // Iterator implementation.
    @NotNull
    @Override
    public Iterator<Recipe> iterator() {
        return recipes.iterator();
    }

    // Getters
    public List<Recipe> getRecipes() {
        return recipes;
    }
}
