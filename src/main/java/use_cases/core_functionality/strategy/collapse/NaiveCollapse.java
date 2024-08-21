package use_cases.core_functionality.strategy.collapse;

import entity.Ingredient;
import entity.ShoppingList;

import java.util.Map;

/**
 * NaiveCollapse is a simple implementation of the CollapseStrategy interface.
 * This strategy collapses ingredients within a shopping list by checking if
 * the ingredient already exists in the list. If the ingredient exists,
 * it adds the quantity of the new ingredient to the existing one.
 * Otherwise, it adds the new ingredient to the list.
 *
 * This implementation does not account for more complex scenarios, such as
 * differences in unit measurement or categories, making it a "naive" approach.
 */
public class NaiveCollapse implements CollapseStrategy {

    /**
     * Collapses the given ingredient within the provided shopping list using
     * a naive strategy. If the ingredient already exists in the shopping list,
     * its quantity is updated by adding the new ingredient's quantity. If the
     * ingredient does not exist, it is added to the shopping list.
     *
     * @param shoppingList The shopping list in which the ingredient should be collapsed.
     * @param ingredient The ingredient to be collapsed in the shopping list.
     */
    @Override
    public void collapse(ShoppingList shoppingList, Ingredient ingredient) {
        Map<String, Ingredient> listItems = shoppingList.getListItemsAsMap();
        String groceryName = ingredient.getIngredientName();

        // Check if the ingredient already exists in the shopping list
        if (listItems.containsKey(groceryName)) {
            // Update the existing ingredient's quantity
            Ingredient item = listItems.get(groceryName);
            double more = ingredient.getQuantity();
            item.addIngredientQuantity(more);
        } else {
            // Add the new ingredient to the shopping list
            listItems.put(groceryName, ingredient);
        }
    }
}
