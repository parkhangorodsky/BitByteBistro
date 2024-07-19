package use_cases._common.xtra.json_processor;

import entity.Nutrition;
import org.json.JSONArray;

import java.util.ArrayList;
import java.util.List;

public interface NutritionJSONHandler extends JSONNullHandler {

     default List<Nutrition> convertJSONtoNutritionList(JSONArray nutritionJSONArray) {
        List<Nutrition> nutritions = new ArrayList<>();
        float calories = nutritionJSONArray.getFloat("calories");
        nutritions.add(new Nutrition("calories", calories, "kcal"));

        String carbs = "CHOCDF.net";
        String sodium = "NA";
        String fats = "FAT";


        List<String> nutrition_categories = new ArrayList<>();
        nutrition_categories.add(carbs);
        nutrition_categories.add(sodium);
        nutrition_categories.add(fats);

        for (String category : nutrition_categories) {
            float quantity = nutritionJSONArray.getJSONObject(category).getFloat("quantity");
            String unit = nutritionJSONArray.getJSONObject(category).getString("unit");
            nutritions.add(new Nutrition(category, quantity, unit));
        }

        return nutritions;
    }

}
