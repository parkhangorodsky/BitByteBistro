package app.config;

import use_cases.search_recipe.interface_adapter.controller.SearchRecipeController;
import use_cases.search_recipe.interface_adapter.presenter.SearchRecipePresenter;
import use_cases.search_recipe.use_case.interactor.SearchRecipeInteractor;

import static app.config.ViewModelConfig.viewManagerModel;
import static app.config.ViewModelConfig.searchRecipeViewModel;
import static app.config.ApiConfig.recipeAPI;

class SearchRecipeConfig {

    static final SearchRecipePresenter presenter = new SearchRecipePresenter(
            viewManagerModel,
            searchRecipeViewModel);

    static final SearchRecipeInteractor interactor = new SearchRecipeInteractor(
            presenter,
            recipeAPI);

    static final SearchRecipeController controller = new SearchRecipeController(
            interactor);

}
