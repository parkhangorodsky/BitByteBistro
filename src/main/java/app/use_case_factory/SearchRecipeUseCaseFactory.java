package app.use_case_factory;

import api.RecipeAPI;
import interface_adapter.controller.SearchRecipeController;
import interface_adapter.presenter.SearchRecipePresenter;
import interface_adapter.view_model.SearchRecipeViewModel;
import interface_adapter.view_model.ViewManagerModel;
import use_case.interactor.SearchRecipeInteractor;
import view.SearchRecipeView;

public class SearchRecipeUseCaseFactory {

    private SearchRecipeUseCaseFactory() {}

    public static SearchRecipeController create(ViewManagerModel viewManagerModel, SearchRecipeViewModel searchRecipeViewModel, RecipeAPI recipeAPI) {

        // Create Presenter
        SearchRecipePresenter searchRecipePresenter = new SearchRecipePresenter(viewManagerModel, searchRecipeViewModel);

        // Create UseCaseInteractor
        SearchRecipeInteractor searchRecipeInteractor = new SearchRecipeInteractor(searchRecipePresenter, recipeAPI);

        //Create Controller
        SearchRecipeController searchRecipeController = new SearchRecipeController(searchRecipeInteractor);

        return searchRecipeController;
    }
}
