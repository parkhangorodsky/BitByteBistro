package use_cases.recipe_to_grocery.use_case.input_data;

import java.util.List;

public class RecipeToGroceryInputData {
    private List<String> recipeNames;
    public RecipeToGroceryInputData(List<String> recipeNames) {
        this.recipeNames = recipeNames;
    }

    public List<String> getRecipeNames() {
        return recipeNames;
    }
}


