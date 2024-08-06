package app.config;

import use_cases.filter_recipe.FilterRecipeController;

import static app.config.ViewModelConfig.myRecipeViewModel;

class FilterRecipeConfig {

    static final FilterRecipeController controller = new FilterRecipeController(myRecipeViewModel);

}
