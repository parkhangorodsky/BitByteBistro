package use_cases.nutrition_display.use_case.input_data;

import entity.Ingredient;

import java.util.List;

public class NutritionDisplayInputData {
    private String title;
    private List<Ingredient> ingredients;

    public NutritionDisplayInputData(String queryString) {
        this.queryString = queryString;
    }
}
