package use_case.output_data;

import entity.Recipe;

import java.util.List;

public class SearchRecipeOutputData {
    private List<Recipe> recipes;

    public SearchRecipeOutputData(List<Recipe> recipes) {
        this.recipes = recipes;
    }
    public List<Recipe> getRecipes() {
        return recipes;
    }


}
