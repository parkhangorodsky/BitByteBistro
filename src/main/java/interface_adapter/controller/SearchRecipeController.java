package interface_adapter.controller;

import use_case.input_data.SearchRecipeInputBoundary;
import use_case.input_data.SearchRecipeInputData;

import java.util.List;
import java.util.Map;

public class SearchRecipeController {
    final SearchRecipeInputBoundary searchRecipeInteractor;

    public SearchRecipeController(SearchRecipeInputBoundary searchRecipeInteractor) {
        this.searchRecipeInteractor = searchRecipeInteractor;
    }

    public void execute(String queryString) {
        SearchRecipeInputData searchRecipeInputData = new SearchRecipeInputData(queryString);
        searchRecipeInteractor.execute(searchRecipeInputData);
    }

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
