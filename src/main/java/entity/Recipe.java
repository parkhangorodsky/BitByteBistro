package entity;

import org.jetbrains.annotations.NotNull;

import java.awt.image.BufferedImage;
import java.time.LocalDateTime;
import java.util.*;

import static use_cases._common.xtra.utility.MD5HashGenerator.generateMD5;

/**
 * Overview: Represents a recipe entity.
 * Encapsulation: This class encapsulates the data that a recipe should / can store.
 * The attribute include name, images, yield, instructions, ingredients, nutrition information, and categorization labels.
 */
public class Recipe implements Comparable<Recipe>{

    private String id; // Unique identifier for the recipe, generated using MD5 hash.
    private String name; //The name of the recipe.
    private BufferedImage image; // The main image associated with the recipe.
    private BufferedImage smallImage; // A smaller version of the image, typically used for previews or thumbnails.
    private int yield; // The number of servings the recipe yields.
    private String instructions; // The instructions or steps to prepare the recipe.

    private List<Ingredient> ingredientList; // List of ingredients required for the recipe.
    private Map<String, Nutrition> nutritionMap; // Map containing nutrition information, where the key is the type of nutrition and the value is the nutrition details.

    List<String> dietLabels; // List of diet labels applicable to the recipe (e.g., vegetarian, vegan).
    List<String> healthLabels; // List of health labels applicable to the recipe (e.g., low-fat, low-sodium).
    List<String> cautions;  // List of cautions or warnings related to the recipe (e.g., allergens).
    List<String> tags; // List of tags or keywords associated with the recipe.
    List<String> cuisineType; // List of cuisine types that the recipe falls under (e.g., Italian, Mexican).
    List<String> mealType; // List of meal types that the recipe can be categorized into (e.g., breakfast, dinner).
    List<String> dishType; // List of dish types (e.g., appetizer, main course).

    /**
     * Default constructor that generates a unique ID for the recipe using the current time and a UUID.
     */
    public Recipe() {
        String input = LocalDateTime.now() + UUID.randomUUID().toString();
        this.id = "recipe_" + generateMD5(input);
    }

    /**
     * Constructs a Recipe with a specified ID. This constructor is used when id is provided by external sources.
     *
     * @param id The unique identifier for the recipe.
     */
    public Recipe(String id) {
        this.id = id;
    }

    /**
     * Getters
     */
    public String getId() {return this.id;}
    public List<Ingredient> getIngredientList() {return this.ingredientList;}
    public String getName() {return this.name;}
    public BufferedImage getImage() {return this.image;}
    public BufferedImage getSmallImage() {return this.smallImage;}
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

    /**
     * Setters
     */
    public void setId(String id) {this.id = id;}
    public void setName(String name) {this.name = name;}
    public void setImage(BufferedImage image) {this.image = image;}
    public void setSmallImage(BufferedImage smallImage) {this.smallImage = smallImage;}
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
    public int compareTo(@NotNull Recipe o) {
        return this.getName().compareToIgnoreCase(o.getName());
    }
}
