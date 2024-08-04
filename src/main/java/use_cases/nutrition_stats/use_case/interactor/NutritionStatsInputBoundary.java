package use_cases.nutrition_stats.use_case.interactor;

import use_cases.nutrition_stats.use_case.input_data.NutritionStatsInputData;

/**
 * The interface for any class that handles NutritionStatsInputData.
 */
public interface NutritionStatsInputBoundary {

    void execute(NutritionStatsInputData nutritionStatsInputData);
}
