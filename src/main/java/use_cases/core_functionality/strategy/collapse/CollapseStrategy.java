package use_cases.core_functionality.strategy.collapse;

import entity.Ingredient;
import entity.ShoppingList;

/**
 * CollapseStrategy is an interface that defines a strategy for collapsing
 * an ingredient in a shopping list. Implementations of this interface
 * will define how an ingredient should be collapsed within a given shopping list.
 *
 * The collapse operation typically involves combining duplicate ingredients
 * or updating quantities based on the specific strategy.
 *
 * Usage:
 * - Implement this interface to define a custom collapse strategy.
 * - Use the collapse method to apply the strategy to a shopping list and ingredient.
 */
public interface CollapseStrategy {

    /**
     * Collapses the given ingredient within the provided shopping list
     * according to the specific strategy implemented.
     *
     * @param shoppingList The shopping list in which the ingredient should be collapsed.
     * @param ingredient The ingredient to be collapsed in the shopping list.
     */
    void collapse(ShoppingList shoppingList, Ingredient ingredient);
}
