package use_cases.search_recipe.use_case.interactor;

import use_cases.search_recipe.use_case.input_data.SearchRecipeInputData;

/**
 * The interface that wny class that handles SearchRecipeInputData.
 */
public interface SearchRecipeInputBoundary {
    void execute(SearchRecipeInputData inputData);
}
