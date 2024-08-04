package frameworks.gui.view_factory.division;

import app.Config;
import use_cases.search_recipe.gui.view.SearchRecipeView;

public class SearchRecipeViewSwingDivision implements ViewFactoryDivision<SearchRecipeView> {
    @Override
    public SearchRecipeView generate(Config config) {
        // Get the NutritionDisplayController from config
        SearchRecipeView searchRecipeView = new SearchRecipeView(
                config.getSearchRecipeViewModel(),
                config.getSearchRecipeController(),
                config.getDisplayRecipeDetailController(),
                config.getAddToMyRecipeController(),
                config.getRecentlyViewedRecipesController(),
                config.getAdvancedSearchRecipeViewModel(),
                config.getViewManagerModel());

        return searchRecipeView;
    }
}
