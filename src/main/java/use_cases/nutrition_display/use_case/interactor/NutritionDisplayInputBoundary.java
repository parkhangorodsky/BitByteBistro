package use_cases.nutrition_display.use_case.interactor;

import entity.Recipe;

public interface NutritionDisplayInputBoundary {
    void execute(Recipe recipe);
}
