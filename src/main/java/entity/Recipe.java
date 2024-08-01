package entity;

import java. util. ArrayList;
import java.util.List;
import java.util.Map;

public class Recipe {

    private String name;
    private String image;
    private int yield;
    private String instructions;

    private List<Ingredient> ingredientList;
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

    public Recipe() {
        super();
        this.rating = 0;
        this.estimatedCostPerServing = 0;
        this.privacyStatus = true;
    }

    /**
     * Getters
     */

    public List<Ingredient> getIngredientList() {
        return this.ingredientList;
    }
    public String getName() {
        return this.name;
    }
    public String getImage() {
        return this.image;
    }
    public Map<String, Nutrition> getNutritionMap() {return this.nutritionMap;}
    public List<String> getDietLabels() {return this.dietLabels;}
    public List<String> getHealthLabels() {return this.healthLabels;}
    public List<String> getCautions() {return this.cautions;}
    public List<String> getTags() {return this.tags;}
    public List<String> getCuisineType() {return this.cuisineType;}
    public List<String> getMealType() {return this.mealType;}
    public List<String> getDishType() {return this.dishType;}
    public String getInstructions() {return this.instructions;}



    @Override
    public String toString() {

        String name = "Menu: <" + this.name + ">\n";
        String instruction = "Instructions: " + this.instructions + "\n";
        StringBuilder ingredients = new StringBuilder().append("<Ingedients>\n>");
        for (Ingredient grocery : this.ingredientList) {ingredients.append(grocery.toString()).append("\n");}
        StringBuilder nutritions = new StringBuilder().append("<Nutritions>\n>");
        for (String nutrition : this.nutritionMap.keySet()) {nutritions.append(nutritionMap.get(nutrition).toString()).append("\n");}
        String rating = "Rating: " + this.rating + "\n";
        String estimated = "Estimated Cost: " + this.estimatedCostPerServing + "\n";
        String privacyStatus = "Privacy Status: " + this.privacyStatus;

        return name + instruction + ingredients + nutritions + rating + estimated + privacyStatus;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public void setYield(int yield) {
        this.yield = yield;
    }

    public void setInstructions(String instructions) {
        this.instructions = instructions;
    }

    public void setIngredientList(List<Ingredient> ingredientList) {
        this.ingredientList = ingredientList;
    }

    public void setNutritionMap(Map<String, Nutrition> nutritionMap) {
        this.nutritionMap = nutritionMap;
    }

    public void setTotalDailyMap(Map<String, Nutrition> totalDailyMap) {
        this.totalDailyMap = totalDailyMap;
    }

    public void setDietLabels(List<String> dietLabels) {
        this.dietLabels = dietLabels;
    }

    public void setHealthLabels(List<String> healthLabels) {
        this.healthLabels = healthLabels;
    }

    public void setCautions(List<String> cautions) {
        this.cautions = cautions;
    }

    public void setTags(List<String> tags) {
        this.tags = tags;
    }

    public void setCuisineType(List<String> cuisineType) {
        this.cuisineType = cuisineType;
    }

    public void setMealType(List<String> mealType) {
        this.mealType = mealType;
    }

    public void setDishType(List<String> dishType) {
        this.dishType = dishType;
    }
}
