package use_cases._common.xtra.json_processor;

import entity.Ingredient;
import entity.Nutrition;
import entity.Recipe;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.*;

/**
 * Overview: The utility interface that provides the default method for
 * converting recipe from JSONObject to the instance of Recipe class.
 */
public interface RecipeJSONHandler extends JSONNullHandler, JSONArrayHandler {

    /**
     * Converts a JSONObject representation of a recipe into a Recipe object.
     *
     * @param recipeJSON the JSONObject containing the recipe data
     * @return a Recipe object containing the parsed data
     */
    default Recipe convertJSONtoRecipe(JSONObject recipeJSON) {

        // Extract necessary information from the JSON object using appropriate methods that matches the data structure.
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

    /**
     * Extracts nutritional information from a JSONObject and returns it as a Map.
     *
     * @param recipeJSON the JSONObject containing the recipe data
     * @param key the key for the JSONObject containing nutritional information.
     *            It is either "totalNutrients" or totalDaily
     * @return a Map containing the nutritional information
     */
    private Map<String, Nutrition> extractNutritionalInfoFromJSONObject(JSONObject recipeJSON, String key) {
        // Get the nutrition JSON object associated with the key.
        JSONObject object = recipeJSON.getJSONObject(key);
        // Initialize a new map
        Map<String, Nutrition> nutritionMap = new HashMap<>();

        // For each nutrition, extract label, quantity, and unit. Then create a Nutrition object.
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

    /**
     * Creates a list of Grocery objects from a JSONArray.
     *
     * @param recipeJSON the JSONObject containing the recipe data
     * @param key the key for the JSONArray containing ingredient information
     * @return a list of Grocery objects
     */
    private List<Ingredient> createIngredientListFromJSONArray(JSONObject recipeJSON, String key) {
            // Get the ingredient list JSON object associated with the key.
        JSONArray ingredients = recipeJSON.getJSONArray(key);
        // Initialize the new List for ingredient.
        List<Ingredient> ingredientList = new ArrayList<>();
        // For each of the ingredient, create an Ingredient object.
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
