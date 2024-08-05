package app.config;

import use_cases.recently_viewed_recipes.RecentlyViewedRecipesController;
import use_cases.recently_viewed_recipes.RecentlyViewedRecipesInteractor;

import static app.config.DataAccessConfig.userDAO;

class RecentlyViewedRecipeConfig {

    static final RecentlyViewedRecipesInteractor interactor = new RecentlyViewedRecipesInteractor(
            userDAO);

    static final RecentlyViewedRecipesController controller = new RecentlyViewedRecipesController(
            interactor);

}
