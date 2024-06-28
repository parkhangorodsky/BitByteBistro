package app;

import api.EdamamRecipeApi;
import api.RecipeAPI;
import interface_adapter.presenter.SearchRecipePresenter;
import interface_adapter.view_model.SearchRecipeViewModel;
import interface_adapter.view_model.ViewManagerModel;
import use_case.interactor.SearchRecipeInteractor;
import use_case.output_data.OutputBoundary;

public class Config {
    private final RecipeAPI recipeAPI = new EdamamRecipeApi();

    public SearchRecipeInteractor searchRecipeInteractor(ViewManagerModel viewManagerModel, SearchRecipeViewModel searchRecipeViewModel) {
        OutputBoundary searchRecipePresenter = new SearchRecipePresenter(viewManagerModel, searchRecipeViewModel);
        return new SearchRecipeInteractor(searchRecipePresenter,recipeAPI);
    }
}
