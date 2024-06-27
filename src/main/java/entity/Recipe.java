package entity;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class Recipe {

    private String name;
    private String image;
    private String instructions;
    private List<Grocery> ingredientList;
    private List<Nutrition> nutritionalInfo;
    private float rating;
    private float estimatedCostPerServing;
    private boolean privacyStatus;

    /**
     * Constructor for Recipe Class.
     * @param name
     * @param image
     * @param instructions
     * @param ingredientList
     * @param nutritionalInfo
     * @param estimatedCostPerServing
     * @param privacyStatus
     */
    public Recipe(String name, String image, String instructions,
                  List<Grocery> ingredientList, List<Nutrition> nutritionalInfo,
                  float estimatedCostPerServing, boolean privacyStatus) {
        this.name = name;
        this.image = image;
        this.instructions = instructions;
        this.ingredientList = ingredientList;
        this.nutritionalInfo = nutritionalInfo;
        this.rating = 0;
        this.estimatedCostPerServing = estimatedCostPerServing;
        this.privacyStatus = true;
    }

    /**
     * Getters
     */

    public List<Grocery> getIngredientList() {
        return this.ingredientList;
    }
    public String getName() {
        return this.name;
    }
    public String getInstructions() {
        return this.instructions;
    }
    public List<Nutrition> getNutritionalInfoList() {
        return this.nutritionalInfo;
    }
    public boolean getPrivacyStatus() {
        return this.privacyStatus;
    }
    public String getImage() {
        return this.image;
    }
    public float getRating() {
        return this.rating;
    }
    public float getEstimatedCostPerServing() {
        return this.estimatedCostPerServing;
    }

    /**
     * Setters
     */
    public void setIngredientList(List<Grocery> ingredientList) {
        this.ingredientList = ingredientList;
    }
    public void setName(String name) {
        this.name = name;
    }
    public void setInstructions(String instructions) {
        this.instructions = instructions;
    }
    public void setNutritionalInfoList(List<Nutrition> nutritionalInfo) {
        this.nutritionalInfo = nutritionalInfo;
    }
    public void setPrivacyStatus(boolean privacyStatus) {
        this.privacyStatus = privacyStatus;
    }
    public void setImage(String image) {
        this.image = image;
    }
    public void setRating(float rating) {
        this.rating = rating;
    }
    public void setEstimatedCostPerServing(float estimatedCostPerServing) {
        this.estimatedCostPerServing = estimatedCostPerServing;
    }

    public static Recipe convertJSONtoRecipe(JSONObject recipeJSON) {
        String name = recipeJSON.getString("label");
        String image = recipeJSON.getString("image");
//        String instructions = recipeJSON.getString("instructions");
        String instructions = "";
        JSONArray ingredients = recipeJSON.getJSONArray("ingredients");
        List<Grocery> ingredientList = new ArrayList<>();
        for (int i = 0; i < ingredients.length(); i++) {
            JSONObject ingredientJSON = ingredients.getJSONObject(i);
            String ingredientID = ingredientJSON.isNull("foodId") ? "" :ingredientJSON.getString("foodId");
            String ingredientName = ingredientJSON.isNull("food") ? "" :ingredientJSON.getString("food");
            float ingredientQuantity = ingredientJSON.isNull("quantity") ? 0.0f :ingredientJSON.getFloat("quantity");
            String ingredientMeasure = ingredientJSON.isNull("measure") ? "" : ingredientJSON.getString("measure");
            String ingredientCategory = ingredientJSON.isNull("foodCategory") ? "" : ingredientJSON.getString("foodCategory");
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
