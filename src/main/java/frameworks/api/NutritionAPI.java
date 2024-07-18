package frameworks.api;

import org.json.JSONArray;
import org.json.JSONObject;
import use_cases.nutrition_display.use_case.input_data.NutritionDisplayInputData;

public interface NutritionAPI {
    JSONArray getNutrition(NutritionDisplayInputData nutritionDisplayInputData);
}

