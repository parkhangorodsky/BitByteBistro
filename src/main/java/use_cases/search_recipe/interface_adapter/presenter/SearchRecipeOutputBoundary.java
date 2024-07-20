package use_cases.search_recipe.interface_adapter.presenter;

import use_cases.search_recipe.use_case.output_data.SearchRecipeOutputData;


/**
 * Overview: Interface defining the boundary for the output of the search recipe use case.
 * Responsibility: Implementations of this interface are responsible for preparing and presenting
 * the search results to the view model or UI.
 */
public interface SearchRecipeOutputBoundary {

    /**
     * Prepares the view for successful recipe search results.
     * Implementations should update the relevant view model, and notify the update to the ViewManager.
     *
     * @param recipes the search results data
     */
    void prepareSuccessView(SearchRecipeOutputData recipes);
}
