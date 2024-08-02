package use_cases.nutrition_stats.use_case.output_data;

import entity.Nutrition;

import java.util.ArrayList;
import java.util.List;

public class NutritionStatsOutputData {
    private List<Nutrition> nutritionInfoTotal;
    private List<Nutrition> nutritionInfoAverage;

    /**
     * Constructs a NutritionStatsOutputData object
     * with the specified nutritional information.
     *
     * @param nutritionInfoTotal the list of recipes found by the search
     */
    public NutritionStatsOutputData(List<Nutrition> nutritionInfoTotal, Integer numberOfRecipes) {
        this.nutritionInfoTotal = nutritionInfoTotal;
        this.nutritionInfoAverage = new ArrayList<>();
        for (Nutrition nutrition : nutritionInfoTotal) {
            Nutrition averageNutrition = new Nutrition(nutrition.getLabel(), nutrition.getQuantity()/numberOfRecipes, nutrition.getUnit());
            this.nutritionInfoAverage.add(averageNutrition);
        }
    }

    /**
     * Gets the nutritional information of a give output data (for a specific recipe).
     * @return the nutritional information of a recipe.
     */
    public List<Nutrition> getNutrition() {
        return nutritionInfoTotal;
    }
    public List<Nutrition> getNutritionInfoAverage() {return nutritionInfoAverage;}
}
