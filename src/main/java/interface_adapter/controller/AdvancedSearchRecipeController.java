package interface_adapter.controller;

import use_case.input_data.AdvancedRecipeSearchInputBoundary;
import use_case.input_data.AdvancedRecipeSearchInputData;

import java.util.List;
import java.util.Map;

public class AdvancedSearchRecipeController {
    AdvancedRecipeSearchInputBoundary advancedRecipeSearchInteractor;

    public AdvancedSearchRecipeController(AdvancedRecipeSearchInputBoundary advancedRecipeSearchInteractor) {
        this.advancedRecipeSearchInteractor = advancedRecipeSearchInteractor;
    }

    public void execute(String queryString,
                   Map<String, Integer> ingredients,
                   List<String> excluded,
                   List<String> diet,
                   List<String> health,
                   List<String> cuisineType,
                   List<String> dishType,
                   List<String> mealType) {
        AdvancedRecipeSearchInputData advancedRecipeSearchInputData = new AdvancedRecipeSearchInputData(queryString,
                ingredients,
                excluded,
                diet,
                health,
                cuisineType,
                dishType,
                mealType);
        advancedRecipeSearchInteractor.execute(advancedRecipeSearchInputData);

    }
}
