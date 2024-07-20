package use_cases.nutrition_display.interface_adapter.presenter;

import use_cases.nutrition_display.use_case.output_data.NutritionDisplayOutputData;

public interface NutritionDisplayOutputBoundary {
    void prepareSuccessView(NutritionDisplayOutputData nutritionDisplayOutputData);
}
