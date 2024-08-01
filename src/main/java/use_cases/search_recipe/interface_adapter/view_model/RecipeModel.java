package use_cases.search_recipe.interface_adapter.view_model;

import entity.Recipe;

public class RecipeModel {
    private Recipe recipe;

    public RecipeModel(Recipe recipe) {
        this.recipe = recipe;
    }

    public Recipe getRecipe() {
        return recipe;
    }

    public void setRecipe(Recipe recipe) {
        this.recipe = recipe;
    }
}
