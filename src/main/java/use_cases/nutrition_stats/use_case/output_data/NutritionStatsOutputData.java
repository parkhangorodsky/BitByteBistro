package use_cases.nutrition_stats.use_case.output_data;

import entity.Nutrition;

import java.util.List;

public class NutritionStatsOutputData {
    private List<Nutrition> nutritionInfo;

    /**
     * Constructs a NutritionStatsOutputData object
     * with the specified nutritional information.
     *
     * @param nutritionInfo the list of recipes found by the search
     */
    public NutritionStatsOutputData(List<Nutrition> nutritionInfo) {
        this.nutritionInfo = nutritionInfo;
    }

    /**
     * Gets the nutritional information of a give output data (for a specific recipe).
     * @return the nutritional information of a recipe.
     */
    public List<Nutrition> getNutrition() {
        return nutritionInfo;
    }
}
