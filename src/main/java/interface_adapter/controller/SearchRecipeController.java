package interface_adapter.controller;

import interface_adapter.view_model.SearchRecipeViewModel;
import use_case.input_data.InputBoundary;
import use_case.input_data.SearchRecipeInputData;

public class SearchRecipeController {
    final InputBoundary searchRecipeInteractor;

    public SearchRecipeController(InputBoundary searchRecipeInteractor) {
        this.searchRecipeInteractor = searchRecipeInteractor;
    }

    public void execute(String queryString) {
        SearchRecipeInputData searchRecipeInputData = new SearchRecipeInputData(queryString);
        searchRecipeInteractor.execute(searchRecipeInputData);
    }

}
