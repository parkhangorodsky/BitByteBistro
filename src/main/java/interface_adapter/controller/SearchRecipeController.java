package interface_adapter.controller;

import use_case.input_data.SearchRecipeInputBoundary;
import use_case.input_data.SearchRecipeInputData;

public class SearchRecipeController {
    final SearchRecipeInputBoundary searchRecipeInteractor;

    public SearchRecipeController(SearchRecipeInputBoundary searchRecipeInteractor) {
        this.searchRecipeInteractor = searchRecipeInteractor;
    }

    public void execute(String queryString) {
        SearchRecipeInputData searchRecipeInputData = new SearchRecipeInputData(queryString);
        searchRecipeInteractor.execute(searchRecipeInputData);
    }

}
