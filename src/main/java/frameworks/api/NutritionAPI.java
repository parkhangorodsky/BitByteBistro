package frameworks.api;

import entity.Nutrition;
import org.json.JSONArray;
import org.json.JSONObject;
import use_cases.nutrition_display.use_case.input_data.NutritionDisplayInputData;

import java.util.List;

public interface NutritionAPI {
    List<Nutrition> getNutrition(NutritionDisplayInputData nutritionDisplayInputData);
}

