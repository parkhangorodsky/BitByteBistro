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

    /**
     * Constructor for the recipe entity.
     * @param name Name of the recipe
     * @param image Image of the recipe
     * @param yield Yields of the recipe
     * @param instructions Instructions of the recipe
     * @param ingredientList List of the ingredients
     * @param nutritionMap Map of nutrition in terms of quantity and unit
     * @param totalDailyMap Map of nutrition in terms of daily percentage
     * @param dietLabels List of diet types that the recipe belongs.
     *                   The diet type labels describe commonly used
     *                   nutrient level aspects of the recipe.
     * @param healthLabels List of health type labels. health type labels
     *                     describe commonly used ingredient level aspects of the recipe.
     * @param cautions List of caution labels.
     * @param tags List of tags.
     * @param cuisineType List of cuisine types labels.
     * @param mealType List of meal type labels. The meal types refer to the
     *                 meals in a day the recipe is commonly consumed in.
     * @param dishType List of dish type labels. The dish types refer to the
     *                 category of food the recipe would fall under.
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
        for (Ingredient grocery : this.ingredientList) {ingredients.append(grocery.toString()).append("\n");}
        StringBuilder nutritions = new StringBuilder().append("<Nutritions>\n>");
        for (String nutrition : this.nutritionMap.keySet()) {nutritions.append(nutritionMap.get(nutrition).toString()).append("\n");}
        String rating = "Rating: " + this.rating + "\n";
        String estimated = "Estimated Cost: " + this.estimatedCostPerServing + "\n";
        String privacyStatus = "Privacy Status: " + this.privacyStatus;

        return name + instruction + ingredients + nutritions + rating + estimated + privacyStatus;
    }
    //method to get a list of groceries from a list of recipe
    public List<Ingredient> getGroceryList(List<Recipe> recipeList) {
        List<Ingredient> groceries = new ArrayList<>();
        for (Recipe recipe : recipeList) {
            for (Ingredient grocery : recipe.getIngredientList()) {
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
