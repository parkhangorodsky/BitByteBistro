package use_case.interactor;

import entity.Grocery;
import entity.Ingredient;
import entity.Nutrition;
import entity.Recipe;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public interface RecipeJSONHandler extends JsonNullHandler{
    public default Recipe convertJSONtoRecipe(JSONObject recipeJSON) {
        String name = recipeJSON.getString("label");
        String image = recipeJSON.getString("image");
//        String instructions = recipeJSON.getString("instructions");
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
        List<Nutrition> nutritionList = new ArrayList<>();
        for (String key : totalNutrients.keySet()) {
            JSONObject totalNutrientJSON = totalNutrients.getJSONObject(key);
            String label = totalNutrientJSON.getString("label");
            float quantity = totalNutrientJSON.getFloat("quantity");
            String unit = totalNutrientJSON.getString("unit");
            Nutrition nutrition = new Nutrition(label, quantity, unit);
            nutritionList.add(nutrition);
        }
        Recipe recipe = new Recipe(name, image, instructions, ingredientList, nutritionList,0.0f, true);
        return recipe;
    }


}
