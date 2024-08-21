package use_cases.core_functionality.view_components;

import app.local.LoggedUserData;
import entity.*;

import java.util.List;
import java.util.ArrayList;
import java.util.Map;
import java.util.LinkedHashMap;

/**
 * The `GroceryListHelper` class provides utility methods for adjusting and consolidating grocery lists.
 * Specifically, it adjusts the quantities of ingredients in a shopping list based on the contents of the user's fridge,
 * and consolidates like ingredients into a single entry for easier display.
 */
public class GroceryListHelper {

    /**
     * Adjusts a shopping list for display by accounting for ingredients already present in the user's fridge
     * and consolidating like ingredients into a single entry.
     *
     * @param originalList The original shopping list to be adjusted.
     * @return A new `ShoppingList` instance with adjusted ingredient quantities and consolidated like ingredients.
     */
    static ShoppingList getAdjustedGroceryListForDisplay(ShoppingList originalList) {
        List<Ingredient> adjustedIngredients = new ArrayList<>();
        List<Ingredient> fridgeItems = LoggedUserData.getLoggedInUser().getFridge().getIngredients();

        // Adjust ingredient quantities based on fridge contents
        for (Ingredient grocery : originalList.getListItems()) {
            double adjustedQuantity = grocery.getQuantity();
            for (Ingredient fridgeItem : fridgeItems) {
                if (grocery.getIngredientName().equalsIgnoreCase(fridgeItem.getIngredientName()) &&
                        grocery.getQuantityUnit().equalsIgnoreCase(fridgeItem.getQuantityUnit())) {
                    adjustedQuantity -= fridgeItem.getQuantity();
                }
            }
            if (adjustedQuantity > 0) {
                Ingredient adjustedIngredient = new Ingredient(grocery.getIngredientID(), grocery.getIngredientName(),
                        grocery.getQuantityUnit(), grocery.getCategory(), adjustedQuantity);
                adjustedIngredients.add(adjustedIngredient);
            }
        }

        // Consolidate like ingredients
        Map<String, Ingredient> consolidatedIngredients = new LinkedHashMap<>();
        for (Ingredient ingredient : adjustedIngredients) {
            String key = ingredient.getIngredientName().toLowerCase() + ingredient.getQuantityUnit().toLowerCase();
            if (consolidatedIngredients.containsKey(key)) {
                consolidatedIngredients.get(key).addIngredientQuantity(ingredient.getQuantity());
            } else {
                consolidatedIngredients.put(key, ingredient);
            }
        }

        // Create a new ShoppingList instance with adjusted and consolidated ingredients
        ShoppingList adjustedShoppingList = new ShoppingList(originalList.getListOwner(), originalList.getShoppingListName());
        adjustedShoppingList.setListItems(new ArrayList<>(consolidatedIngredients.values()));
        adjustedShoppingList.setEstimatedTotalCost(originalList.getEstimatedTotalCost());
        adjustedShoppingList.setRecipes(originalList.getRecipes());
        return adjustedShoppingList;
    }
}
