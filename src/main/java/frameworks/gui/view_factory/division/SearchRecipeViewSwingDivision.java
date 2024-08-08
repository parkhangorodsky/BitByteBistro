package frameworks.gui.view_factory.division;

import app.config.Config;
import use_cases._common.gui_common.abstractions.View;
import use_cases.search_recipe.gui.view.SearchRecipeView;

public class SearchRecipeViewSwingDivision implements ViewFactoryDivision {
    @Override
    public View generate(Config config) {
        // Get the NutritionDisplayController from config
        return new SearchRecipeView(
                config.getSearchRecipeViewModel(),
                config.getSearchRecipeController(),
                config.getDisplayRecipeDetailController(),
                config.getAddToMyRecipeController(),
                config.getRecentlyViewedRecipesController(),
                config.getAddNewGroceryListController(),
                config.getCoreFunctionalityController(),
                config.getAdvancedSearchRecipeViewModel(),
                config.getViewManagerModel());
    }
}
