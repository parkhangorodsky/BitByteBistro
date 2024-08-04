package use_cases.recipe_to_grocery.use_case.input_data;

import entity.LoggedUserData;
import entity.Recipe;
import entity.ShoppingList;
import entity.User;


/**
 * Input data class for the use case of converting recipes to a grocery list.
 * Contains the user information needed for the conversion process.
 */
public class RecipeToGroceryInputData {
    private Recipe recipe;
    private ShoppingList shoppingList;

    /**
     * Constructs a RecipeToGroceryInputData object with the specified user.
     *
     * @param recipe The Recipe object representing the recipe being added to a Grocery List Item.
     */
    public RecipeToGroceryInputData(Recipe recipe) {
        this.recipe = recipe;
    }

    public RecipeToGroceryInputData(Recipe recipe, ShoppingList shoppingList) {
        this.recipe = recipe;
        this.shoppingList = shoppingList;
    }

    public Recipe getRecipe () {
        return recipe;
    }

    public ShoppingList getShoppingList () {
        return shoppingList;
    }
}
