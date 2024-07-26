package use_cases.recipe_to_grocery.use_case.interactor;

import entity.Ingredient;
import entity.ShoppingList;
import entity.Recipe;
import entity.User;

import frameworks.api.RecipeAPI;

import use_cases.recipe_to_grocery.use_case.input_data.RecipeToGroceryInputData;
import use_cases.recipe_to_grocery.interface_adapter.presenter.RecipeToGroceryOutputBoundary;
import use_cases.recipe_to_grocery.use_case.output_data.RecipeToGroceryOutputData;

import use_cases._common.xtra.json_processor.RecipeJSONHandler;

import java.util.ArrayList;
import java.util.List;

/**
 * Interactor class that handles the "convert recipes to grocery list" use case.
 * It receives input data, executes the conversion logic, and sends output data to the presenter.
 */
public class RecipeToGroceryInteractor implements RecipeToGroceryInputBoundary, RecipeJSONHandler {

    private RecipeToGroceryOutputBoundary recipeToGroceryPresenter;

    /**
     * Constructs a RecipeToGroceryInteractor with the provided presenter and RecipeAPI dependency.
     *
     * @param recipeToGroceryPresenter The presenter responsible for displaying the output data.
     * @param recipeAPI                The API service used for accessing recipe information.
     */
    public RecipeToGroceryInteractor(RecipeToGroceryOutputBoundary recipeToGroceryPresenter, RecipeAPI recipeAPI) {
        this.recipeToGroceryPresenter = recipeToGroceryPresenter;
    }

    /**
     * Executes the use case of converting recipes to a grocery list based on the input data.
     * Retrieves user's recipes, processes them, and prepares the output for the presenter.
     *
     * @param recipeToGroceryInputData The input data containing the user information for conversion.
     */
    @Override
    public void execute(RecipeToGroceryInputData recipeToGroceryInputData) {
        User user = recipeToGroceryInputData.getUser();
        List<Recipe> recipes = user.getRecipes();

        // Convert recipes to grocery list
        ShoppingList shoppingList = getGroceryList(recipes, user);
        List<ShoppingList> shoppingLists = new ArrayList<>();
        shoppingLists.add(shoppingList);

        // Prepare output data and notify presenter
        RecipeToGroceryOutputData recipeToGroceryOutputData = new RecipeToGroceryOutputData(shoppingLists);
        recipeToGroceryPresenter.prepareSuccessView(recipeToGroceryOutputData);
    }

    /**
     * Converts a list of recipes into a single ShoppingList for the given user.
     *
     * @param recipeList The list of recipes to convert.
     * @param user       The user for whom the grocery list is prepared.
     * @return A ShoppingList containing all ingredients from the recipes.
     */
    public ShoppingList getGroceryList(List<Recipe> recipeList, User user) {
        List<Ingredient> groceries = new ArrayList<>();
        for (Recipe recipe : recipeList) {
            for (Ingredient grocery : recipe.getIngredientList()) {
                if (groceries.contains(grocery)) {
                    Ingredient item = groceries.get(groceries.indexOf(grocery));
                    float more = grocery.getIngredientQuantity();
                    item.addIngredientQuantity(more);
                } else {
                    groceries.add(grocery);
                }
            }
        }
        List<ShoppingList> existingLists = user.getShoppingLists();
        String listName = "list1";
        if (!existingLists.isEmpty()) {
            listName = "list" + existingLists.size();
        }
        return new ShoppingList(user.getUserName(), listName, groceries);
    }
}
