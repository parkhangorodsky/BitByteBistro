package use_cases.search_recipe.factory;

import frameworks.api.RecipeAPI;

import use_cases.search_recipe.interface_adapter.controller.SearchRecipeController;
import use_cases.search_recipe.interface_adapter.presenter.SearchRecipePresenter;

import use_cases.search_recipe.interface_adapter.view_model.SearchRecipeViewModel;
import use_cases._common.interface_adapter_common.view_model.models.ViewManagerModel;
import use_cases.search_recipe.use_case.interactor.SearchRecipeInteractor;

/**
 * Overview: Factory class for creating instances related to the Search Recipe use case.
 * Procedure: This class provides static public methods to create various components involved in
 * searching recipes, including presenter, interactor, and controller.
 * Encapsulation: This class encapsulates the logic for intatiating the core classes for the search recipe use case.
 */
public class SearchRecipeUseCaseFactory {

    /**
     * Private constructor to prevent instantiation of this factory class.
     * All methods are static, so instances of this class are not needed.
     */
    private SearchRecipeUseCaseFactory() {}

    /**
     * Creates a new instance of SearchRecipePresenter.
     *
     * @param viewManagerModel   The view manager model to be used by the presenter.
     * @param searchRecipeViewModel The search recipe view model to be used by the presenter.
     * @return A new SearchRecipePresenter instance.
     */
    public static SearchRecipePresenter createSearchRecipePresenter(ViewManagerModel viewManagerModel, SearchRecipeViewModel searchRecipeViewModel) {
        return new SearchRecipePresenter(viewManagerModel, searchRecipeViewModel);
    }

    /**
     * Creates a new instance of SearchRecipeInteractor.
     *
     * @param searchRecipePresenter The presenter to be used by the interactor.
     * @param recipeAPI            The RecipeAPI instance to be used by the interactor.
     * @return A new SearchRecipeInteractor instance.
     */
    public static SearchRecipeInteractor createsearchRecipeInteractor(SearchRecipePresenter searchRecipePresenter, RecipeAPI recipeAPI) {
        return new SearchRecipeInteractor(searchRecipePresenter, recipeAPI);
    }

    /**
     * Creates a new instance of SearchRecipeController.
     *
     * @param searchRecipeInteractor The interactor to be used by the controller.
     * @return A new SearchRecipeController instance.
     */
    public static SearchRecipeController createController(SearchRecipeInteractor searchRecipeInteractor) {

        return new SearchRecipeController(searchRecipeInteractor);
    }
}
