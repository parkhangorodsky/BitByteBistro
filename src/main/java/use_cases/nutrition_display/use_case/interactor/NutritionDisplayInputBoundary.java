package use_cases.nutrition_display.use_case.interactor;

import entity.Recipe;
import use_cases.nutrition_display.use_case.input_data.NutritionDisplayInputData;

public interface NutritionDisplayInputBoundary {
    void execute(NutritionDisplayInputData nutritionDisplayInputData);
}
