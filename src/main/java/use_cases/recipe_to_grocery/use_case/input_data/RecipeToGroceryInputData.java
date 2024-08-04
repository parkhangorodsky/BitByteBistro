package use_cases.recipe_to_grocery.use_case.input_data;

import entity.LoggedUserData;
import entity.Recipe;
import entity.ShoppingList;
import entity.User;

import java.util.AbstractList;
import java.util.ArrayList;

/**
 * Input data class for the use case of converting recipes to a grocery list.
 * Contains the user information needed for the conversion process.
 */
public class RecipeToGroceryInputData {
    private ArrayList<Recipe> recipes;
    private ShoppingList shoppingList;

    /**
     * Constructs a RecipeToGroceryInputData object with the specified user.
     *
     * @param recipes The Recipe object representing the recipe(s) being converted to a Grocery List Item.
     */
    public RecipeToGroceryInputData(ArrayList<Recipe> recipes) {
        this.recipes = recipes;
    }

    public RecipeToGroceryInputData(ArrayList<Recipe> recipes, ShoppingList shoppingList) {
        this.recipes = recipes;
        this.shoppingList = shoppingList;
    }

    public ArrayList<Recipe> getRecipes () {
        return recipes;
    }

    public ShoppingList getShoppingList () {
        return shoppingList;
    }
}
