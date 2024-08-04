package use_cases.nutrition_display.use_case.input_data;

import entity.Ingredient;


import java.util.List;

/**
 * Input data for retrieving nutritional information. This class encapsulates the request criteria.
 */
public class NutritionDisplayInputData {
    private String title;
    private List<Ingredient> ingredients;

    /**
     * Class constructor for nutritional information input data.
     * @param title title of the recipe.
     * @param ingredients list of ingredients (including quantities and units) of the recipe.
     *
     */
    public NutritionDisplayInputData(String title, List<Ingredient> ingredients) {
        this.title = title;
        this.ingredients = ingredients;
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
}
