package use_cases.search_recipe.interface_adapter.controller;

import use_cases.search_recipe.use_case.interactor.SearchRecipeInputBoundary;
import use_cases.search_recipe.use_case.input_data.SearchRecipeInputData;

import java.util.List;

/**
 * Controller for handling search recipe requests.
 * It receives input from the view, constructs the appropriate input data, and passes it to the interactor.
 * It encapsulates the logic for handling recipe search input, and constructing appropriate data structures.
 */
public class SearchRecipeController {
    final SearchRecipeInputBoundary searchRecipeInteractor; // The use case interactor for the recipe search.

    /**
     * Class constructor for SearchRecipeController.
     * @param searchRecipeInteractor The use case interactor to which the controller will delegate actions.
     */
    public SearchRecipeController(SearchRecipeInputBoundary searchRecipeInteractor) {
        this.searchRecipeInteractor = searchRecipeInteractor;
    }

    /**
     * Executes a basic search for recipes with the given query string.
     *
     * @param queryString the main query string for searching recipes
     */
    public void execute(String queryString) {
        SearchRecipeInputData searchRecipeInputData = new SearchRecipeInputData(queryString);
        searchRecipeInteractor.execute(searchRecipeInputData);
    }

    /**
     * Executes an advanced search for recipes with the given query string and additional criteria.
     *
     * @param queryString the main query string for searching recipes
     * @param excluded the list of ingredients to exclude
     * @param diet the list of dietary restrictions
     * @param health the list of health labels
     * @param cuisineType the list of cuisine types
     * @param dishType the list of dish types
     * @param mealType the list of meal types
     */
    public void execute(String queryString,
                        List<String> excluded,
                        List<String> diet,
                        List<String> health,
                        List<String> cuisineType,
                        List<String> dishType,
                        List<String> mealType) {
        SearchRecipeInputData searchRecipeInputData = new SearchRecipeInputData(queryString,
                excluded,
                diet,
                health,
                cuisineType,
                dishType,
                mealType);
        searchRecipeInteractor.execute(searchRecipeInputData);
    }
}
