package use_cases.nutrition_display.use_case.output_data;

import entity.Nutrition;
import entity.Recipe;

import java.util.List;

/**
 * Output data for the nutrition display use case.
 * This class encapsulates the nutritional information (List<Nutrition>).
 */
public class NutritionDisplayOutputData {
    private List<Nutrition> nutritionInfo;

    /**
     * Constructs a NutritionDisplayOutputData object
     * with the specified nutritional information.
     *
     * @param nutritionInfo the list of recipes found by the search
     */
    public NutritionDisplayOutputData(List<Nutrition> nutritionInfo) {
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
