package use_cases._common.xtra.json_processor;

import entity.Ingredient;
import entity.Nutrition;
import entity.Recipe;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.*;

public interface RecipeJSONHandler extends JSONNullHandler, JSONArrayHandler {
    default Recipe convertJSONtoRecipe(JSONObject recipeJSON) {
        String name = recipeJSON.getString("label");
        String image = recipeJSON.getJSONObject("images").getJSONObject("REGULAR").getString("url");
        int yield = recipeJSON.getInt("yield");
        List<String> dietLabels = JSONStringArrayToList(handleNullJSONArray(recipeJSON, "dietLabels"));
        List<String> healthLabels = JSONStringArrayToList(handleNullJSONArray(recipeJSON, "healthLabels"));
        List<String> cautions = JSONStringArrayToList(handleNullJSONArray(recipeJSON, "cautions"));
        String instructions = "";
        List<Ingredient> ingredientList = createIngredientListFromJSONArray(recipeJSON, "ingredients");
        Map<String, Nutrition> nutritionMap = extractNutritionalInfoFromJSONObject(recipeJSON,"totalNutrients");
        Map<String, Nutrition> totalDailyMap = extractNutritionalInfoFromJSONObject(recipeJSON,"totalDaily");
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
                dishType);
        return recipe;
    }

    private Map<String, Nutrition> extractNutritionalInfoFromJSONObject(JSONObject recipeJSON, String key) {
        JSONObject object = recipeJSON.getJSONObject(key);
        Map<String, Nutrition> nutritionMap = new HashMap<>();
        for (String nutri : object.keySet()) {
            JSONObject nutritionJSON = object.getJSONObject(nutri);
            String label = nutritionJSON.getString("label");
            float quantity = nutritionJSON.getFloat("quantity");
            String unit = nutritionJSON.getString("unit");
            Nutrition nutrition = new Nutrition(label, quantity, unit);
            nutritionMap.put(label, nutrition);
        }
        return nutritionMap;
    }

    private List<Ingredient> createIngredientListFromJSONArray(JSONObject recipeJSON, String key) {
        JSONArray ingredients = recipeJSON.getJSONArray(key);
        List<Ingredient> ingredientList = new ArrayList<>();
        for (int i = 0; i < ingredients.length(); i++) {
            JSONObject ingredientJSON = ingredients.getJSONObject(i);
            String ingredientID = handleNullString(ingredientJSON, "foodId");
            String ingredientName = handleNullString(ingredientJSON, "food");
            float ingredientQuantity = handleNullFloat(ingredientJSON, "quantity");
            String ingredientMeasure = handleNullString(ingredientJSON, "measure");
            String ingredientCategory = handleNullString(ingredientJSON, "foodCategory");
            Ingredient ingredient = new Ingredient(ingredientID, ingredientName, ingredientMeasure, ingredientCategory, ingredientQuantity);
            ingredientList.add(ingredient);
        }
        return ingredientList;
    }









}
