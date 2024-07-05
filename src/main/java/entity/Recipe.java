package entity;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Recipe {


    private String name;
    private String image;
    private int yield;
    private String instructions;

    private List<Grocery> ingredientList;
    private Map<String, Nutrition> nutritionMap;
    private Map<String, Nutrition> totalDailyMap;

    private float rating;
    private float estimatedCostPerServing;
    private boolean privacyStatus;

    List<String> dietLabels;
    List<String> healthLabels;
    List<String> cautions;
    List<String> tags;
    List<String> cuisineType;
    List<String> mealType;
    List<String> dishType;

    /**
     * Constructor for Recipe Class.
     * @param name
     * @param image
     * @param instructions
     * @param ingredientList
     * @param nutritionMap
     * @param estimatedCostPerServing
     * @param privacyStatus
     */
    public Recipe(String name,
                  String image,
                  int yield,
                  String instructions,
                  List<Grocery> ingredientList,
                  Map<String, Nutrition> nutritionMap,
                  Map<String, Nutrition> totalDailyMap,
                  List<String> dietLabels,
                  List<String> healthLabels,
                  List<String> cautions,
                  List<String> tags,
                  List<String> cuisineType,
                  List<String> mealType,
                  List<String> dishType,
                  float estimatedCostPerServing) {

        this.name = name;
        this.image = image;
        this.yield = yield;
        this.instructions = instructions;
        this.ingredientList = ingredientList;
        this.nutritionMap = nutritionMap;
        this.totalDailyMap = totalDailyMap;

        this.dietLabels = dietLabels;
        this.healthLabels = healthLabels;
        this.cautions = cautions;
        this.tags = tags;
        this.cuisineType = cuisineType;
        this.mealType = mealType;
        this.dishType = dishType;

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
    public Map<String, Nutrition> getNutritionalInfoList() {
        return this.nutritionMap;
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
    public void setNutritionalInfoList(Map<String, Nutrition> nutritionMap) {
        this.nutritionMap = nutritionMap;
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

    @Override
    public String toString() {

        String name = "Menu: <" + this.name + ">\n";
        String instruction = "Instructions: " + this.instructions + "\n";
        StringBuilder ingredients = new StringBuilder().append("<Ingredients>\n>");
        for (Grocery grocery : this.ingredientList) {ingredients.append(grocery.toString()).append("\n");}
        StringBuilder nutritions = new StringBuilder().append("<Nutritions>\n>");
        for (String nutrition : this.nutritionMap.keySet()) {nutritions.append(nutritionMap.get(nutrition).toString()).append("\n");}
        String rating = "Rating: " + this.rating + "\n";
        String estimated = "Estimated Cost: " + this.estimatedCostPerServing + "\n";
        String privacyStatus = "Privacy Status: " + this.privacyStatus;

        return name + instruction + ingredients + nutritions + rating + estimated + privacyStatus;

    }
    //method to get a list of groceries from a list of recipe
    public List<Grocery> getGroceryList(List<Recipe> recipeList) {
        List<Grocery> groceries = new ArrayList<>();
        for (Recipe recipe : recipeList) {
            for (Grocery grocery : recipe.getIngredientList()) {
                if (groceries.contains(grocery)) {
                    groceries.get(groceries.indexOf(grocery)).quantity += grocery.quantity;
                } else {
                    groceries.add(grocery);
                }
            }
        }
        return groceries;
    }
}
