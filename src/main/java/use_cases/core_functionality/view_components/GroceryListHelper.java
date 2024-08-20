package use_cases.core_functionality.view_components;

import app.local.LoggedUserData;
import entity.*;

import java.util.List;
import java.util.ArrayList;
import java.util.Map;
import java.util.LinkedHashMap;


public class GroceryListHelper {

    static ShoppingList getAdjustedGroceryListForDisplay(ShoppingList originalList) {
        List<Ingredient> adjustedIngredients = new ArrayList<>();
        List<Ingredient> fridgeItems = LoggedUserData.getLoggedInUser().getFridge().getIngredients();

        for (Ingredient grocery : originalList.getListItems()) {
            float adjustedQuantity = grocery.getQuantity();
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

        ShoppingList adjustedShoppingList = new ShoppingList(originalList.getListOwner(), originalList.getShoppingListName());
        adjustedShoppingList.setListItems(new ArrayList<>(consolidatedIngredients.values()));
        adjustedShoppingList.setEstimatedTotalCost(originalList.getEstimatedTotalCost());
        adjustedShoppingList.setRecipes(originalList.getRecipes());
        return adjustedShoppingList;
    }
}

