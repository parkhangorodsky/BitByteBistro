package use_cases.search_recipe.interface_adapter.presenter;

import use_cases.search_recipe.use_case.output_data.SearchRecipeOutputData;

public interface SearchRecipeOutputBoundary {

    void prepareSuccessView(SearchRecipeOutputData recipes);
}
