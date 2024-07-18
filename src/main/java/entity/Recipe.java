package entity;

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

    /**
     * Constructor for Recipe Class.
     * @param name
     * @param image
     * @param instructions
     * @param ingredientList
     * @param nutritionMap
     */
    public Recipe(String name,
                  String image,
                  int yield,
                  String instructions,
                  List<Ingredient> ingredientList,
                  Map<String, Nutrition> nutritionMap,
                  Map<String, Nutrition> totalDailyMap,
                  List<String> dietLabels,
                  List<String> healthLabels,
                  List<String> cautions,
                  List<String> tags,
                  List<String> cuisineType,
                  List<String> mealType,
                  List<String> dishType) {

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

    @Override
    public String toString() {

        String name = "Menu: <" + this.name + ">\n";
        String instruction = "Instructions: " + this.instructions + "\n";
        StringBuilder ingredients = new StringBuilder().append("<Ingedients>\n>");
        for (Ingredient ingredient : this.ingredientList) {ingredients.append(ingredient.toString()).append("\n");}
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
