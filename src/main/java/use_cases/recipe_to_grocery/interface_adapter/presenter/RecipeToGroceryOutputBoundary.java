package use_cases.recipe_to_grocery.interface_adapter.presenter;

import use_cases.recipe_to_grocery.use_case.output_data.RecipeToGroceryOutputData;

public interface RecipeToGroceryOutputBoundary {

    void prepareSuccessView(RecipeToGroceryOutputData recipes);
}
