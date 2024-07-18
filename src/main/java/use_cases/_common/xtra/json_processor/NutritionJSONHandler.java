package use_cases._common.xtra.json_processor;

import entity.Nutrition;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public interface NutritionJSONHandler extends JSONNullHandler {

     default Nutrition convertJSONtoNutritionList(JSONArray nutritionJSONArray) {
        Nutrition nutritions = new ArrayList<>();
        float calories = nutritionJSON.getFloat("calories");
        nutritions.add(new Nutrition("calories", calories, "kcal"));

        String carbsString = "CHOCDF.net";
        String sodiumString = "NA";
        String fatsString = "FAT";
        List<String> nutrition_categories = new ArrayList<>();

        nutrition_categories.add(carbsString);
        nutrition_categories.add(sodiumString);
        nutrition_categories.add(fatsString);

        for (String category : nutrition_categories) {
            float quantity = nutritionJSON.getJSONObject(category).getFloat("quantity");
            String unit = nutritionJSON.getJSONObject(category).getString("unit");
            nutritions.add(new Nutrition(category, quantity, unit));
        }

        return nutritions;
    }

    private List<String> JSONStringArrayToList(JSONArray jsonArray) {
        List<String> list = new ArrayList<>();
        for (int i = 0; i < jsonArray.length(); i++) {
            list.add(jsonArray.getString(i));
        }
        return list;
    }

}
