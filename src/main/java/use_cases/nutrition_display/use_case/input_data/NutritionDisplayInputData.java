package use_cases.nutrition_display.use_case.input_data;

import entity.Ingredient;
import entity.Nutrition;
import use_cases.nutrition_display.use_case.interactor.NutritionDisplayInputBoundary;
import use_cases.nutrition_display.use_case.interactor.NutritionDisplayInteractor;

import java.util.List;

public class NutritionDisplayInputData {
    private String title;
    private List<Ingredient> ingredients;

    public NutritionDisplayInputData(String title, List<Ingredient> ingredients) {
        this.title = title;
        this.ingredients = ingredients;
    }
    public String getTitle() {return title;}
    public List<Ingredient> getIngredients() {return ingredients;}
}
