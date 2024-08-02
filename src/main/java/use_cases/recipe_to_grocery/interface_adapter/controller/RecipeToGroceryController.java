package use_cases.recipe_to_grocery.interface_adapter.controller;

import entity.Recipe;
import entity.ShoppingList;
import entity.User;
import entity.LoggedUserData;
import use_cases.recipe_to_grocery.use_case.input_data.RecipeToGroceryInputData;
import use_cases.recipe_to_grocery.use_case.interactor.RecipeToGroceryInputBoundary;

import java.util.ArrayList;

/**
 * Controller for handling the conversion of recipes to a grocery list of ingredients.
 * This class receives input from the view, constructs the appropriate input data, and passes it to the interactor.
 * It encapsulates the logic for handling recipe conversion and constructing appropriate data structures.
 */
public class RecipeToGroceryController {

    private final RecipeToGroceryInputBoundary recipeToGroceryInteractor;

    /**
     * Constructs a RecipeToGroceryController with the specified interactor and authentication service.
     *
     * @param recipeToGroceryInteractor The use case interactor to which the controller delegates actions.
     */
    public RecipeToGroceryController(RecipeToGroceryInputBoundary recipeToGroceryInteractor) {
        this.recipeToGroceryInteractor = recipeToGroceryInteractor;
    }

    /**
     * Converts a list of recipes to a shopping list of ingredients for the logged-in user.
     *
     * @param recipes A list of recipes from which the controller creates shopping lists.
     */

    public void convertRecipesToGroceryList(ArrayList<Recipe> recipes, ShoppingList shoppingList) {
        // Prepare input data
        RecipeToGroceryInputData recipeToGroceryInputData = new RecipeToGroceryInputData(recipes, shoppingList);

        // Execute the use case
        recipeToGroceryInteractor.execute(recipeToGroceryInputData);
    }
}
