package use_cases._common.xtra.json_processor;

import entity.Nutrition;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.*;

/**
 * The utility interface that provides the default method for converting nutritional information
 * from JSONObject to a List<Nutrition>, where each Nutrition is a nutrition fact for a specific recipe.
 */
public interface NutritionJSONHandler extends JSONNullHandler {


    /**
     * converts raw output (JSONObject) from the NutritionAPI into a list of Nutrition (so the nutritional information)
     * for a given recipe. Currently, the nutritional information contains the calories, carbs, sodium, and fats.
     *
     * @param nutritionJSONObject JSONObject retrieved from the NutritionAPI
     * @return a List<Nutrition> which contains the Nutrition objects with the label, unit, and quantity of each: calories,
     * carbs, sodium, and fats.
     */
     default List<Nutrition> convertJSONtoNutritionList(JSONObject nutritionJSONObject) {
         List<Nutrition> nutritions = new ArrayList<>();

         if (nutritionJSONObject.has("error")) {
             nutritions.add(new Nutrition());
         } else {
             float calories = nutritionJSONObject.getFloat("calories");
             Nutrition calorieFact = new Nutrition("calories", calories, "kcal");
             nutritions.add(calorieFact);

             JSONObject totalNutrients = nutritionJSONObject.getJSONObject("totalNutrients");
             Set<String> keys = totalNutrients.keySet();
             for (String category : keys) {
                 String label = totalNutrients.getJSONObject(category).getString("label");
                 float quantity = totalNutrients.getJSONObject(category).getFloat("quantity");
                 String unit = totalNutrients.getJSONObject(category).getString("unit");

                 Nutrition nutritionFact = new Nutrition(label, quantity, unit);
                 nutritions.add(nutritionFact);
             }

             // Sort the nutritions list using Collections.sort with a custom comparator
             nutritions.sort(Comparator.comparing(Nutrition::getLabel));
         }

         return nutritions;
    }
}
