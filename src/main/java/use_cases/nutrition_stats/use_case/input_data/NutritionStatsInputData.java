package use_cases.nutrition_stats.use_case.input_data;

import entity.Ingredient;

import java.util.List;

public class NutritionStatsInputData {
    private String title;
    private List<Ingredient> ingredients;
    private int numberOfRecipes;

    /**
     * Class constructor for nutritional information input data.
     * @param title title of the recipe.
     * @param ingredients list of ingredients (including quantities and units) of the recipe.
     *
     */
    public NutritionStatsInputData(String title, List<Ingredient> ingredients, int numberOfRecipes) {
        this.title = title;
        this.ingredients = ingredients;
        this.numberOfRecipes = numberOfRecipes;
    }

    //Getters
    /**
     * Gets the title of the recipe.
     * @return The title of the recipe.
     */
    public String getTitle() {return title;}

    /**
     * Gets the list of ingredients for the recipe.
     * @return The list of ingredients (including quantities and units) for the recipe.
     */
    public List<Ingredient> getIngredients() {return ingredients;}

    /**
     * Gets the number of recipes in a shopping list.
     * @return The number of recipe in the shopping list.
     */
    public int getNumberOfRecipes() {return numberOfRecipes;}
}
