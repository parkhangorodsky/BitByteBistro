package entity;

import use_cases._common.xtra.hashing.MD5HashGenerator;

import java.time.LocalDateTime;
import java.util.*;

public class Recipe implements MD5HashGenerator {

    private String name;
    private String image;
    private String smallImage;
    private int yield;
    private String instructions;

    private List<Ingredient> ingredientList;
    private Map<String, Nutrition> nutritionMap;

    private String id;

    List<String> dietLabels;
    List<String> healthLabels;
    List<String> cautions;
    List<String> tags;
    List<String> cuisineType;
    List<String> mealType;
    List<String> dishType;

//    /**
//     * Constructor for the recipe entity.
//     * @param name Name of the recipe
//     * @param image Image of the recipe
//     * @param yield Yields of the recipe
//     * @param instructions Instructions of the recipe
//     * @param ingredientList List of the ingredients
//     * @param nutritionMap Map of nutrition in terms of quantity and unit
//     * @param totalDailyMap Map of nutrition in terms of daily percentage
//     * @param dietLabels List of diet types that the recipe belongs.
//     *                   The diet type labels describe commonly used
//     *                   nutrient level aspects of the recipe.
//     * @param healthLabels List of health type labels. health type labels
//     *                     describe commonly used ingredient level aspects of the recipe.
//     * @param cautions List of caution labels.
//     * @param tags List of tags.
//     * @param cuisineType List of cuisine types labels.
//     * @param mealType List of meal type labels. The meal types refer to the
//     *                 meals in a day the recipe is commonly consumed in.
//     * @param dishType List of dish type labels. The dish types refer to the
//     *                 category of food the recipe would fall under.
//     */
    public Recipe() {
        String input = LocalDateTime.now() + UUID.randomUUID().toString();
        this.id = "recipe_" + generateMD5(input);
    }

    public Recipe(String id) {
        this.id = id;
    }
    /**
     * Getters
     */

    public String getId() {return this.id;}
    public List<Ingredient> getIngredientList() {return this.ingredientList;}
    public String getName() {return this.name;}
    public String getImage() {return this.image;}
    public String getSmallImage() {return this.smallImage;}
    public int getYield() {return this.yield;}
    public Map<String, Nutrition> getNutritionMap() {return this.nutritionMap;}
    public List<String> getDietLabels() {return this.dietLabels;}
    public List<String> getHealthLabels() {return this.healthLabels;}
    public List<String> getCautions() {return this.cautions;}
    public List<String> getTags() {return this.tags;}
    public List<String> getCuisineType() {return this.cuisineType;}
    public List<String> getMealType() {return this.mealType;}
    public List<String> getDishType() {return this.dishType;}
    public String getInstructions() {return this.instructions;}
    
    public void setId(String id) {this.id = id;}
    public void setName(String name) {this.name = name;}
    public void setImage(String image) {this.image = image;}
    public void setSmallImage(String smallImage) {this.smallImage = smallImage;}
    public void setYield(int yield) {this.yield = yield;}
    public void setInstructions(String instructions) {this.instructions = instructions;}
    public void setIngredientList(List<Ingredient> ingredientList) {this.ingredientList = ingredientList;}
    public void setNutritionMap(Map<String, Nutrition> nutritionMap) {this.nutritionMap = nutritionMap;}
    public void setDietLabels(List<String> dietLabels) {this.dietLabels = dietLabels;}
    public void setHealthLabels(List<String> healthLabels) {this.healthLabels = healthLabels;}
    public void setCautions(List<String> cautions) {this.cautions = cautions;}
    public void setTags(List<String> tags) {this.tags = tags;}
    public void setCuisineType(List<String> cuisineType) {this.cuisineType = cuisineType;}
    public void setMealType(List<String> mealType) {this.mealType = mealType;}
    public void setDishType(List<String> dishType) {this.dishType = dishType;}


    @Override
    public String toString() {

        String name = "Menu: <" + this.name + ">\n";
        String instruction = "Instructions: " + this.instructions + "\n";
        StringBuilder ingredients = new StringBuilder().append("<Ingedients>\n>");
        for (Ingredient grocery : this.ingredientList) {ingredients.append(grocery.toString()).append("\n");}
        StringBuilder nutritions = new StringBuilder().append("<Nutritions>\n>");
        for (String nutrition : this.nutritionMap.keySet()) {nutritions.append(nutritionMap.get(nutrition).toString()).append("\n");}


        return name + instruction + ingredients + nutritions;
    }
}
