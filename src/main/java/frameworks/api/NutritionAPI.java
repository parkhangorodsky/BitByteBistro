package frameworks.api;

import entity.Nutrition;
import org.json.JSONArray;
import org.json.JSONObject;
import use_cases.nutrition_stats.use_case.input_data.NutritionStatsInputData;

import java.util.List;

public interface NutritionAPI {
    List<Nutrition> getNutrition(NutritionStatsInputData nutritionStatsInputData);
}

