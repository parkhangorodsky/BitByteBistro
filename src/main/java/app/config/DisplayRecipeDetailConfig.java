package app.config;

import use_cases.display_recipe_detail.DisplayRecipeDetailController;
import use_cases.display_recipe_detail.DisplayRecipeDetailInteractor;
import use_cases.display_recipe_detail.DisplayRecipeDetailPresenter;

class DisplayRecipeDetailConfig {

    static final DisplayRecipeDetailPresenter presenter = new DisplayRecipeDetailPresenter();

    static final DisplayRecipeDetailInteractor interactor = new DisplayRecipeDetailInteractor(
            presenter);

    static final DisplayRecipeDetailController controller = new DisplayRecipeDetailController(
            interactor);

}
