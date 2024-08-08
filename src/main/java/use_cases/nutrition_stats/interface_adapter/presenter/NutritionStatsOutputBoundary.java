package use_cases.nutrition_stats.interface_adapter.presenter;

import use_cases.nutrition_stats.use_case.output_data.NutritionStatsOutputData;

/**
 * Overview: Interface defining the boundary for the output of the nutrition display use case.
 * Responsibility: Implementations of this interface are responsible for preparing and presenting
 * the nutritional information for a specific recipe to the view model or UI.
 */
public interface NutritionStatsOutputBoundary {

    /**
     * Prepares the view for successful nutrition stats results.
     * Implementations should update the relevant view model, and notify the update to the ViewManager.
     *
     * @param nutritionStatsOutputData the search results data
     */
    void prepareSuccessView(NutritionStatsOutputData nutritionStatsOutputData);
}

