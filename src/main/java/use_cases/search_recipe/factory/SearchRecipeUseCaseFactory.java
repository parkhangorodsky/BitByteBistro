package use_cases.search_recipe.factory;

import frameworks.api.RecipeAPI;

import use_cases.search_recipe.interface_adapter.controller.SearchRecipeController;
import use_cases.search_recipe.interface_adapter.presenter.SearchRecipePresenter;

import use_cases.search_recipe.interface_adapter.view_model.SearchRecipeViewModel;
import use_cases._common.interface_adapter_common.view_model.models.ViewManagerModel;
import use_cases.search_recipe.use_case.interactor.SearchRecipeInteractor;


public class SearchRecipeUseCaseFactory {

    private SearchRecipeUseCaseFactory() {}

    public static SearchRecipePresenter createSearchRecipePresenter(ViewManagerModel viewManagerModel, SearchRecipeViewModel searchRecipeViewModel) {
        return new SearchRecipePresenter(viewManagerModel, searchRecipeViewModel);
    }
    public static SearchRecipeInteractor createsearchRecipeInteractor(SearchRecipePresenter searchRecipePresenter, RecipeAPI recipeAPI) {
        return new SearchRecipeInteractor(searchRecipePresenter, recipeAPI);
    }
    public static SearchRecipeController createController(SearchRecipeInteractor searchRecipeInteractor) {

        return new SearchRecipeController(searchRecipeInteractor);
    }
}
