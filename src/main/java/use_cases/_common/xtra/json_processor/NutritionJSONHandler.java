package use_cases._common.xtra.json_processor;

import entity.Nutrition;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * The utility interface that provides the default method for converting nutritional information
 * from JSONObject to a List<Nutrition>, where each Nutrition is a nutrition fact for a specific recipe.
 */
public interface NutritionJSONHandler extends JSONNullHandler {


    /**
     * converts raw output (JSONObject) from the NutritionAPI into a list of Nutrition (so the nutritional information)
     * for a given recipe. Currently, the nutritional information contains the calories, carbs, sodium, and fats. There
     * are some extra print statements throughout which serve as a temporary "view", since the current view will not be
     * completed before Phase 1.
     *
     * @param nutritionJSONObject JSONObject retrieved from the NutritionAPI
     * @return a List<Nutrition> which contains the Nutrition objects with the label, unit, and quantity of each: calories,
     * carbs, sodium, and fats.
     */
     default List<Nutrition> convertJSONtoNutritionList(JSONObject nutritionJSONObject) {
         // temp line for the temp view:
         System.out.println("The recipe has the following nutritional info:");

         List<Nutrition> nutritions = new ArrayList<>();

         if (nutritionJSONObject.has("error")) {
             nutritions.add(new Nutrition());
         } else {
             float calories = nutritionJSONObject.getFloat("calories");
             Nutrition calorieFact = new Nutrition("calories", calories, "kcal");
             nutritions.add(calorieFact);
             // temp lines for the temp view:
             System.out.println(calorieFact);

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

                 Nutrition nutritionFact = new Nutrition(label, quantity, unit);
                 nutritions.add(nutritionFact);

                 // temp line for the temp view:
                 System.out.println(nutritionFact);
             }
         }
         // temp lines for the temp view:
         if (nutritions.getFirst().getLabel().isEmpty()) {
            System.out.println("Could not retrieve nutritional information for this recipe.");
         }
         System.out.println("---spacer for the temp. view ---");
        return nutritions;
    }

}
