package frameworks.gui.view_factory.division;

import app.config.Config;
import use_cases._common.gui_common.view.HomeView;
import use_cases._common.interface_adapter_common.view_model.models.ViewManagerModel;
import use_cases.add_new_grocery_list.AddNewGroceryListController;
import use_cases.add_to_my_recipe.AddToMyRecipeController;
import use_cases.core_functionality.CoreFunctionalityController;
import use_cases.display_recipe_detail.DisplayRecipeDetailController;
import use_cases.nutrition_stats.interface_adapter.controller.NutritionStatsController;
import use_cases.nutrition_stats.interface_adapter.view_model.NutritionStatsViewModel;
import use_cases.recently_viewed_recipes.RecentlyViewedRecipesController;

public class HomeViewSwingDivision implements ViewFactoryDivision<HomeView> {
    @Override
    public HomeView generate(Config config) {
        return new HomeView(
                config.getViewManagerModel(),
                config.getNutritionStatsController(),
                config.getNutritionStatsViewModel(),
                config.getAddToMyRecipeController(),
                config.getCoreFunctionalityController(),
                config.getRecentlyViewedRecipesController(),
                config.getAddNewGroceryListController(),
                config.getDisplayRecipeDetailController());
    }
}
