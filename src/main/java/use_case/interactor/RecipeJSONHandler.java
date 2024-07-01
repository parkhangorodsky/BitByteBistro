package use_case.interactor;

import entity.Grocery;
import entity.Ingredient;
import entity.Nutrition;
import entity.Recipe;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.*;

public interface RecipeJSONHandler extends JsonNullHandler{
    public default Recipe convertJSONtoRecipe(JSONObject recipeJSON) {
        String name = recipeJSON.getString("label");
        String image = recipeJSON.getJSONObject("images").getJSONObject("REGULAR").getString("url");

        int yield = recipeJSON.getInt("yield");

        List<String> dietLabels = JSONStringArrayToList(handleNullJSONArray(recipeJSON, "dietLabels"));
        List<String> healthLabels = JSONStringArrayToList(handleNullJSONArray(recipeJSON, "healthLabels"));
        List<String> cautions = JSONStringArrayToList(handleNullJSONArray(recipeJSON, "cautions"));

        String instructions = "";

        JSONArray ingredients = recipeJSON.getJSONArray("ingredients");
        List<Grocery> ingredientList = new ArrayList<>();
        for (int i = 0; i < ingredients.length(); i++) {
            JSONObject ingredientJSON = ingredients.getJSONObject(i);
            String ingredientID = handleNullString(ingredientJSON, "foodId");
            String ingredientName = handleNullString(ingredientJSON, "food");
            float ingredientQuantity = handleNullFloat(ingredientJSON, "quantity");
            String ingredientMeasure = handleNullString(ingredientJSON, "measure");
            String ingredientCategory = handleNullString(ingredientJSON, "foodCategory");
            Ingredient ingredient = new Ingredient(ingredientID, ingredientName, ingredientMeasure, ingredientCategory);
            Grocery grocery = new Grocery(ingredient, ingredientQuantity);
            ingredientList.add(grocery);
        }

        JSONObject totalNutrients = recipeJSON.getJSONObject("totalNutrients");
        Map<String, Nutrition> nutritionMap = new HashMap<>();
        for (String key : totalNutrients.keySet()) {
            JSONObject totalNutrientJSON = totalNutrients.getJSONObject(key);
            String label = totalNutrientJSON.getString("label");
            float quantity = totalNutrientJSON.getFloat("quantity");
            String unit = totalNutrientJSON.getString("unit");
            Nutrition nutrition = new Nutrition(label, quantity, unit);
            nutritionMap.put(label, nutrition);
        }

        JSONObject totalDaily = recipeJSON.getJSONObject("totalDaily");
        Map<String, Nutrition> totalDailyMap = new HashMap<>();
        for (String key : totalDaily.keySet()) {
            JSONObject totalDailyJSON = totalNutrients.getJSONObject(key);
            String label = totalDailyJSON.getString("label");
            float quantity = totalDailyJSON.getFloat("quantity");
            String unit = totalDailyJSON.getString("unit");
            Nutrition nutrition = new Nutrition(label, quantity, unit);
            totalDailyMap.put(label, nutrition);
        }

        List<String> tags = JSONStringArrayToList(handleNullJSONArray(recipeJSON, "tags"));

        List<String> cuisineType = JSONStringArrayToList(handleNullJSONArray(recipeJSON, "cuisineType"));
        List<String> mealType = JSONStringArrayToList(handleNullJSONArray(recipeJSON, "mealType"));
        List<String> dishType = JSONStringArrayToList(handleNullJSONArray(recipeJSON, "dishType"));

        Recipe recipe = new Recipe(name,
                image,
                yield,
                instructions,
                ingredientList,
                nutritionMap,
                totalDailyMap,
                dietLabels,
                healthLabels,
                cautions,
                tags,
                cuisineType,
                mealType,
                dishType,
                0.0f);
        return recipe;
    }

    private List<String> JSONStringArrayToList(JSONArray jsonArray) {
        List<String> list = new ArrayList<>();
        for (int i = 0; i < jsonArray.length(); i++) {
            list.add(jsonArray.getString(i));
        }
        return list;
    }


}
