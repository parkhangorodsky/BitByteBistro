package use_cases.nutrition_display.use_case.output_data;

import entity.Nutrition;
import entity.Recipe;

import java.util.List;

public class NutritionDisplayOutputData {
    private Nutrition nutritionInfo;

    public NutritionDisplayOutputData(Nutrition nutritionInfo) {
        this.nutritionInfo = nutritionInfo;
    }

    public Nutrition getNutrition() {
        return nutritionInfo;
    }
}
