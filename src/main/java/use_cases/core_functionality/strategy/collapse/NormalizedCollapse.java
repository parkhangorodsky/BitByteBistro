package use_cases.core_functionality.strategy.collapse;

import entity.Ingredient;
import entity.ShoppingList;
import use_cases.core_functionality.strategy.normalize.*;

import java.util.Map;

/**
 * The `NormalizedCollapse` class implements the `CollapseStrategy` interface and
 * provides a strategy for collapsing ingredients in a shopping list with normalization.
 * This strategy uses a normalization step to ensure that ingredient names are
 * consistent, preventing duplication due to minor differences (e.g., capitalization).
 *
 * This approach is useful when dealing with inconsistent data, where similar ingredients
 * might be listed under slightly different names.
 */
public class NormalizedCollapse implements CollapseStrategy {
    // Strategy for normalizing ingredient names (e.g., case-insensitive comparison)
    NormalizeStrategy normalizeStrategy = new StringNormalize();

    /**
     * Collapses the given ingredient within the provided shopping list using
     * a normalization strategy. The ingredient name is first normalized
     * (e.g., converted to lowercase) to ensure consistency. If the normalized
     * ingredient already exists in the shopping list, its quantity is updated
     * by adding the new ingredient's quantity. If the normalized ingredient
     * does not exist, it is added to the shopping list.
     *
     * @param shoppingList The shopping list in which the ingredient should be collapsed.
     * @param ingredient The ingredient to be collapsed in the shopping list.
     */
    @Override
    public void collapse(ShoppingList shoppingList, Ingredient ingredient) {
        // Normalize the ingredient name to ensure consistency
        Map<String, Ingredient> listItems = shoppingList.getListItemsAsMap();
        String normalizedGroceryName = normalizeStrategy.normalize(ingredient.getIngredientName());

        // Check if the normalized ingredient already exists in the shopping list
        if (listItems.containsKey(normalizedGroceryName)) {
            // Update the existing ingredient's quantity
            Ingredient item = listItems.get(normalizedGroceryName);
            float more = ingredient.getQuantity();
            item.addIngredientQuantity(more);
        } else {
            // Add the new normalized ingredient to the shopping list
            listItems.put(normalizedGroceryName, ingredient);
        }
    }
}
