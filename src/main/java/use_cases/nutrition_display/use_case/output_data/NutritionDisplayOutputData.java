package use_cases.nutrition_display.use_case.output_data;

import entity.Nutrition;
import entity.Recipe;

import java.util.List;

public class NutritionDisplayOutputData {
    private List<Nutrition> nutritionInfo;

    public NutritionDisplayOutputData(List<Nutrition> nutritionInfo) {
        this.nutritionInfo = nutritionInfo;
    }

    public List<Nutrition> getNutrition() {
        return nutritionInfo;
    }
}
