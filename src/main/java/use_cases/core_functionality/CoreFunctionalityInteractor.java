package use_cases.core_functionality;

import app.local.LoggedUserData;
import entity.*;
import frameworks.data_access.UserDataAccessInterface;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.LinkedHashMap;


public class CoreFunctionalityInteractor implements CoreFunctionalityInputBoundary {
    CoreFunctionalityPresenter presenter;
    UserDataAccessInterface userDAO;

    /**
     * Constructs an CoreFunctionalityInteractor with the given presenter and user data access object.
     *
     * @param presenter The presenter responsible for preparing the view.
     * @param userDAO   The data access object for user-related data.
     */
    public CoreFunctionalityInteractor(CoreFunctionalityPresenter presenter, UserDataAccessInterface userDAO) {
        this.presenter = presenter;
        this.userDAO = userDAO;
    }

    /**
     * Executes the action of adding a recipe to the logged-in user's grocery list.
     * If the recipe already exists in the user's grocery list, it should allow it to be added again (since people may want more than one serving).
     * It adds the recipe to the user's grocery list, updates the data access object, and prepares a success view.
     *
     * @param inputData The input data required for adding the recipe.
     */
    @Override
    public void execute(CoreFunctionalityInputData inputData) {
        User user = LoggedUserData.getLoggedInUser();
        ShoppingList shoppingList = inputData.getShoppingList();
        Recipe recipe = inputData.getRecipe();

        // Use getAdjustedGroceryList instead of getGroceryList
        ShoppingList updatedShoppingList = getAdjustedGroceryList(recipe, shoppingList);
        user.addRecipe(recipe);
        //userDAO.addRecipe(user, recipe);

        // UPDATE USER (LOCALLY AND IN DATABASE)
        //some way to update an existing shopping list instead of adding the updated one on top
        user.addShoppingList(updatedShoppingList);
        //userDAO.addShoppingList(user, updatedShoppingList);

        CoreFunctionalityOutputData outputData = new CoreFunctionalityOutputData(updatedShoppingList, inputData.getParentModel());
        presenter.prepareSuccessView(outputData);
    }

    public ShoppingList getAdjustedGroceryList(Recipe recipe, ShoppingList shoppingList) {
        List<Ingredient> groceries = new ArrayList<>(shoppingList.getListItems()); // Copy the original list
        List<Ingredient> fridgeItems = LoggedUserData.getLoggedInUser().getFridge().getIngredients(); // Get fridge items

        for (Ingredient grocery : recipe.getIngredientList()) {
            for (Ingredient fridgeItem : fridgeItems) {
                if (grocery.getIngredientName().equals(fridgeItem.getIngredientName()) && grocery.getQuantityUnit().equals(fridgeItem.getQuantityUnit())) {
                    float adjustedQuantity = grocery.getQuantity() - fridgeItem.getQuantity();
                    if (adjustedQuantity > 0) {
                        grocery.setQuantity(adjustedQuantity);
                    } else {
                        grocery.setQuantity(0);
                    }
                }
            }

            if (grocery.getQuantity() > 0) {
                groceries.add(grocery);
            }
        }

        // Remove items with zero quantity
        groceries.removeIf(ingredient -> ingredient.getQuantity() <= 0);

        ShoppingList adjustedShoppingList = new ShoppingList(shoppingList.getListOwner(), shoppingList.getShoppingListName());
        adjustedShoppingList.setListItems(groceries);
        adjustedShoppingList.setEstimatedTotalCost(shoppingList.getEstimatedTotalCost());
        adjustedShoppingList.setRecipes(shoppingList.getRecipes());
        return adjustedShoppingList;
    }

    public ShoppingList getAggregatedGroceryList(Recipe recipe, ShoppingList shoppingList) {
        Map<String, Ingredient> aggregatedIngredients = new LinkedHashMap<>();

        // Aggregate the recipe ingredients
        for (Ingredient ingredient : recipe.getIngredientList()) {
            String key = ingredient.getIngredientName() + ingredient.getQuantityUnit();
            if (aggregatedIngredients.containsKey(key)) {
                Ingredient existing = aggregatedIngredients.get(key);
                existing.addIngredientQuantity(ingredient.getQuantity());
            } else {
                aggregatedIngredients.put(key, ingredient);
            }
        }

        // Add these aggregated ingredients to the shopping list
        List<Ingredient> aggregatedList = new ArrayList<>(aggregatedIngredients.values());
        shoppingList.setListItems(aggregatedList);

        return shoppingList;
    }

}
