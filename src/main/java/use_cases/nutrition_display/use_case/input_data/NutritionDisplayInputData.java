package use_cases.nutrition_display.use_case.input_data;

import entity.Grocery;
import entity.Ingredient;

import java.util.List;

public class NutritionDisplayInputData {
    private String title;
    private List<Grocery> ingredients;

    public NutritionDisplayInputData(String title, List<Grocery> ingredients) {
        this.title = title;
        this.ingredients = ingredients;
    }
}
