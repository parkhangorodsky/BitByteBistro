package frameworks.gui.view_factory.division;

import app.config.Config;
import use_cases._common.gui_common.abstractions.View;
import use_cases.add_to_my_recipe.MyRecipeView;

public class MyRecipeViewSwingDivision implements ViewFactoryDivision {
    @Override
    public View generate(Config config) {
        return new MyRecipeView(
                config.getMyRecipeViewModel(),
                config.getFilterRecipeController(), config.getRecentlyViewedRecipesController(), config.getDisplayRecipeDetailController(), config.getCoreFunctionalityController(), config.getAddNewGroceryListController());
    }
}
