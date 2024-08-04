package frameworks.gui.view_factory.division;

import app.Config;
import use_cases.recipe_to_grocery.gui.RecipeToGroceryView;

public class RecipeToGroceryViewSwingDivision implements ViewFactoryDivision<RecipeToGroceryView> {

    @Override
    public RecipeToGroceryView generate(Config config) {
        RecipeToGroceryView recipeToGroceryView = new RecipeToGroceryView(
                config.getRecipeToGroceryViewModel(),
                config.getRecipeToGroceryController(),
                config.getAuthenticationService(),
                config.getViewManagerModel());

        return recipeToGroceryView;
    }
}
