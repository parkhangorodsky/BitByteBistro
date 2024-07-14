package use_cases.nutrition_display.interface_adapter.controller;

import entity.Recipe;
import use_cases.nutrition_display.use_case.interactor.NutritionDisplayInputBoundary;
import use_cases.nutrition_display.use_case.input_data.NutritionDisplayInputData;

public class NutritionDisplayController {
    final NutritionDisplayInputBoundary nutritionDisplayInteractor;

    public NutritionDisplayController(NutritionDisplayInputBoundary nutritionDisplayInteractor) {
        this.nutritionDisplayInteractor = nutritionDisplayInteractor;
    }
    public void execute(Recipe recipe) {
        nutritionDisplayInteractor.execute(recipe);
    }
}
