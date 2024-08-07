package use_cases._common.xtra.utility;

import entity.Ingredient;
import entity.Nutrition;
import entity.Recipe;
import entity.builder.DefaultRecipeBuilder;
import entity.builder.RecipeBuilder;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.awt.image.BufferedImage;
import java.util.*;

import static use_cases._common.xtra.utility.JSONArrayHandler.JSONStringArrayToList;
import static use_cases._common.xtra.utility.BufferedImageLoader.loadBufferedRoundImage;
import static use_cases._common.xtra.utility.JSONNullHandler.*;


/**
 * Overview: The utility interface that provides the default method for
 * converting recipe from JSONObject to the instance of Recipe class.
 */
public class RecipeJSONHandler {


    public static List<Recipe> convertJSONResponseToRecipe(JSONArray responseRecipe) throws JSONException {
        List<Recipe> recipeList = new ArrayList<>();
        for (int i = 0; i < responseRecipe.length(); i++) {
            JSONObject recipeJSON = responseRecipe.getJSONObject(i).getJSONObject("recipe");
            Recipe recipe =  convertJSONtoRecipe(recipeJSON);
            recipeList.add(recipe);
        }

        return recipeList;
    }
    /**
     * Converts a JSONObject representation of a recipe into a Recipe object.
     *
     * @param recipeJSON the JSONObject containing the recipe data
     * @return a Recipe object containing the parsed data
     */
    private static Recipe convertJSONtoRecipe(JSONObject recipeJSON) {
        // Extract necessary information from the JSON object using appropriate methods that matches the data structure.
        String uri = recipeJSON.getString("uri");
        String name = recipeJSON.getString("label");
        String imageUrl = recipeJSON.getJSONObject("images").getJSONObject("REGULAR").getString("url");
        BufferedImage image = loadBufferedRoundImage(imageUrl);
        String smallImageRul = recipeJSON.getJSONObject("images").getJSONObject("THUMBNAIL").getString("url");
        BufferedImage smallImage = loadBufferedRoundImage(smallImageRul);
        int yield = recipeJSON.getInt("yield");
        List<String> dietLabels = JSONStringArrayToList(handleNullJSONArray(recipeJSON, "dietLabels"));
        List<String> healthLabels = JSONStringArrayToList(handleNullJSONArray(recipeJSON, "healthLabels"));
        List<String> cautions = JSONStringArrayToList(handleNullJSONArray(recipeJSON, "cautions"));
        String instructions = recipeJSON.getString("url");
        List<Ingredient> ingredientList = createIngredientListFromJSONArray(recipeJSON, "ingredients");
        Map<String, Nutrition> nutritionMap = extractNutritionalInfoFromJSONObject(recipeJSON,"totalNutrients", "totalDaily");
        List<String> tags = JSONStringArrayToList(handleNullJSONArray(recipeJSON, "tags"));
        List<String> cuisineType = JSONStringArrayToList(handleNullJSONArray(recipeJSON, "cuisineType"));
        List<String> mealType = JSONStringArrayToList(handleNullJSONArray(recipeJSON, "mealType"));
        List<String> dishType = JSONStringArrayToList(handleNullJSONArray(recipeJSON, "dishType"));

        RecipeBuilder builder = new DefaultRecipeBuilder(uri.substring(uri.length() - 32))
                .buildName(name)
                .buildImage(image)
                .buildSmallImage(smallImage)
                .buildDietLabels(dietLabels)
                .buildHealthLabels(healthLabels)
                .buildCautions(cautions)
                .buildNutritionMap(nutritionMap)
                .buildTags(tags)
                .buildCuisineType(cuisineType)
                .buildMealType(mealType)
                .buildDishType(dishType)
                .buildIngredientList(ingredientList)
                .buildYield(yield)
                .buildInstruction(instructions)
                ;

        return builder.get();
    }

    /**
     * Extracts nutritional information from a JSONObject and returns it as a Map.
     *
     * @param recipeJSON the JSONObject containing the recipe data
     * @param quantityKey the key for the JSONObject containing nutritional information.
     *            It is either "totalNutrients" or totalDaily
     * @return a Map containing the nutritional information
     */
    private static Map<String, Nutrition> extractNutritionalInfoFromJSONObject(JSONObject recipeJSON, String quantityKey, String percentKey) {
        // Get the nutrition JSON object associated with the key.
        JSONObject quantityObject = recipeJSON.getJSONObject(quantityKey);
        JSONObject percentObject = recipeJSON.getJSONObject(percentKey);

        // Initialize a new map
        Map<String, Nutrition> nutritionMap = new TreeMap<>();

        // For each nutrition, extract label, quantity, and unit. Then create a Nutrition object.
        for (String nutri : quantityObject.keySet()) {
            JSONObject nutritionJSON = quantityObject.getJSONObject(nutri);
            String label = nutritionJSON.getString("label");
            float quantity = nutritionJSON.getFloat("quantity");
            String unit = nutritionJSON.getString("unit");
            Nutrition nutrition;
            if (percentObject.has(nutri)) {
                float percent = percentObject.getJSONObject(nutri).getFloat("quantity");
                nutrition = new Nutrition(label, quantity, unit, percent);
            } else {
                nutrition = new Nutrition(label, quantity, unit);
            }
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
    private static List<Ingredient> createIngredientListFromJSONArray(JSONObject recipeJSON, String key) {
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
