package use_cases._common.xtra.json_processor;

import entity.Nutrition;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public interface NutritionJSONHandler extends JSONNullHandler {

     default List<Nutrition> convertJSONtoNutritionList(JSONObject nutritionJSONObject) {
         List<Nutrition> nutritions = new ArrayList<>();

         if (nutritionJSONObject.has("error")) {
             nutritions.add(new Nutrition());
         } else {
             float calories = nutritionJSONObject.getFloat("calories");
             nutritions.add(new Nutrition("calories", calories, "kcal"));

             String carbs = "CHOCDF";
             String sodium = "NA";
             String fats = "FAT";

             List<String> nutrition_categories = new ArrayList<>();
             nutrition_categories.add(carbs);
             nutrition_categories.add(sodium);
             nutrition_categories.add(fats);

             for (String category : nutrition_categories) {
                 JSONObject totalNutrients = nutritionJSONObject.getJSONObject("totalNutrients");

                 String label = totalNutrients.getJSONObject(category).getString("label");
                 float quantity = totalNutrients.getJSONObject(category).getFloat("quantity");
                 String unit = totalNutrients.getJSONObject(category).getString("unit");

                 nutritions.add(new Nutrition(label, quantity, unit));
             }
         }

        return nutritions;
    }

}
