package use_cases.nutrition_display.interface_adapter.presenter;

import use_cases.nutrition_display.use_case.output_data.NutritionDisplayOutputData;

/**
 * Overview: Interface defining the boundary for the output of the nutrition display use case.
 * Responsibility: Implementations of this interface are responsible for preparing and presenting
 * the nutritional information for a specific recipe to the view model or UI.
 */
public interface NutritionDisplayOutputBoundary {

    /**
     * Prepares the view for successful nutrition display results.
     * Implementations should update the relevant view model, and notify the update to the ViewManager.
     *
     * @param nutritionDisplayOutputData the search results data
     */
    void prepareSuccessView(NutritionDisplayOutputData nutritionDisplayOutputData);
}
