package app.use_case_factory;

import api.RecipeAPI;
import interface_adapter.controller.AdvancedSearchRecipeController;
import interface_adapter.controller.SearchRecipeController;
import interface_adapter.presenter.SearchRecipePresenter;
import interface_adapter.view_model.AdvancedSearchRecipeViewModel;
import interface_adapter.view_model.SearchRecipeViewModel;
import interface_adapter.view_model.ViewManagerModel;
import use_case.interactor.AdvancedSearchRecipeInteractor;
import use_case.interactor.SearchRecipeInteractor;
import view.SearchRecipeView;

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
