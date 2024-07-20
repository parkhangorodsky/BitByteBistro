package use_cases.recipe_to_grocery.interface_adapter.controller;

import entity.ShoppingList;
import use_cases.recipe_to_grocery.interface_adapter.presenter.RecipeToGroceryPresenter;
import use_cases.recipe_to_grocery.use_case.input_data.RecipeToGroceryInputBoundary;
import use_cases.recipe_to_grocery.use_case.input_data.RecipeToGroceryInputData;
import use_cases.recipe_to_grocery.use_case.interactor.RecipeToGroceryInteractor;

import entity.Recipe;
import java.util.List;

/**
 * Controller for handling converting recipes to a grocery list of ingredients.
 * It receives input from the view, constructs the appropriate input data, and passes it to the interactor.
 * It encapsulates the logic for handling recipe conversion and constructing appropriate data structures.
 */

public class RecipeToGroceryController {

    private final RecipeToGroceryInputBoundary recipeToGroceryInteractor;
    private final RecipeToGroceryPresenter recipeToGroceryPresenter;

    /**
     * Class constructor for RecipeToGroceryController.
     * @param recipeToGroceryInteractor The use case interactor to which the controller will delegate actions.
     * @param recipeToGroceryPresenter The presenter to format output for the view.
     */

    public RecipeToGroceryController(RecipeToGroceryInputBoundary recipeToGroceryInteractor,
                                     RecipeToGroceryPresenter recipeToGroceryPresenter) {
        this.recipeToGroceryInteractor = recipeToGroceryInteractor;
        this.recipeToGroceryPresenter = recipeToGroceryPresenter;
    }

    /**
     * Fetches a list of recipes from the data source.
     * @return List of Recipe objects fetched from the data source.
     */
    public List<Recipe> fetchRecipes() {
        return recipeToGroceryInteractor.fetchRecipes();
    }

    /**
     * Converts a list of recipes to a shopping list of ingredients.
     * @param recipes List of Recipe objects to convert.
     * @return List of ingredients as a shopping list.
     */
    public ShoppingList convertToShoppingList(List<Recipe> recipes) {
        ConvertRecipeToShoppingListInputData inputData = new ConvertRecipeToShoppingListInputData(recipes);
        convertRecipeToShoppingListInteractor.convertToShoppingList(inputData);
        return convertRecipeToShoppingListPresenter.getShoppingList();
    }
}

public void execute(String queryString) {
        RecipeToGroceryInputData recipeToGroceryInputData = new RecipeToGroceryInputData(queryString);
        recipeToGroceryInteractor.execute(recipeToGroceryInputData);
    }
}






